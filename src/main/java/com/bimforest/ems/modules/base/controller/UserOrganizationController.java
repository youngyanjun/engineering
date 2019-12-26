package com.bimforest.ems.modules.base.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.construction.entity.EngineeringOrganizationUser;
import com.bimforest.ems.modules.construction.mapper.EngineeringOrganizationUserMapper;
import com.bimforest.ems.modules.sys.mapper.SysUserMapper;
import com.bimforest.ems.modules.base.service.BaseOrganizationService;
import com.bimforest.ems.modules.base.vo.DeleteUserByOrganizationVO;
import com.bimforest.ems.modules.base.entity.UserOrganization;
import com.bimforest.ems.modules.base.mapper.UserOrganizationMapper;
import com.bimforest.ems.modules.base.service.UserOrganizationService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 项目信息
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-06
 */
@Slf4j
@RestController
@RequestMapping("/api/base/report/wengineering-base-info/")
public class UserOrganizationController {
    @Autowired
    BaseOrganizationService organization;
    @Autowired
    EngineeringOrganizationUserMapper organizationMapper;
    @Autowired
    UserOrganizationMapper userOrganizationMapper;
    @Autowired
    SysUserMapper userMapper;
    @Autowired
    UserOrganizationService userOrganizationService;

    @ApiOperation("获取用户组织架构")
    @GetMapping("getUserOrganizationList")
    public CommonResponse getUserOrganizationList(String engineeringId) {
        List list = organization.getUserOrganizationByUserId(engineeringId);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, list);
    }

    /*-------------------------------项目配置 | 组织结构-------------------------------*/
    @ApiOperation("获取用户组织架构关联用户")
    @GetMapping("getOrganizationUserList")
    public CommonResponse getOrganizationUserList(String organizationId) {
        //判断组织架构是否是根节点
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotEmpty(organizationId), "id", organizationId);
        EngineeringOrganizationUser engineeringOrganizationUser = organizationMapper.selectOne(queryWrapper);
        if (engineeringOrganizationUser.getParentId().equals("")) {
            //查询所有用户
            QueryWrapper query = new QueryWrapper();
            query.eq(StringUtils.isNotEmpty(organizationId), "organization_id", organizationId);

            return new CommonResponse(ResponseTypeEnums.SUCCESS, userOrganizationMapper.selectList(query));
        }
        //查询子目录用户
        //查询所有用户
        QueryWrapper query = new QueryWrapper();
        query.eq(StringUtils.isNotEmpty(organizationId), "child_id", engineeringOrganizationUser.getOrgId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, userOrganizationMapper.selectList(query));

    }

    @OperationLog(module = "项目配置", action = "添加组织成员")
    @ApiOperation(value = "添加成员", notes = "orgParentId 为组织最高级别 orgId ,childId 为其他非最高级父类 orgId" +
            "userId 为选中用户ID")
    @PostMapping("AddUser")
    public CommonResponse AddUser(@RequestBody List<UserOrganization> addUser) {
      /*  //查出当前组织已存在的用户
        if (addUser.size() >= 1) {
            userOrganizationService.selectList(addUser.get(0).getOrganizationId());
        } else {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        //判断用户是否已经存在,存在则删除记录,重新添加,否者添加记录
        for (int i = 0; i < addUser.size(); i++) {
        }*/
        userOrganizationService.saveBatch(addUser);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation(value = "获取所有可用成员列表", notes = "childId 为其他非最高级父类 子节点ID")
    @GetMapping("getUser")
    public CommonResponse getUser(String userName) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, userMapper.selectListUser(userName));
    }

    @OperationLog(module = "项目配置", action = "移除组织用户")
    @ApiOperation(value = "删除组织中的用户", notes = "")
    @PostMapping("deleteOrganizationUser")
    public CommonResponse deleteOrganizationUser(@RequestBody DeleteUserByOrganizationVO userId) {

        //判断组织架构是否是根节点
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotEmpty(userId.getOrganizationId()), "id", userId.getOrganizationId());
        EngineeringOrganizationUser engineeringOrganizationUser = organizationMapper.selectOne(queryWrapper);
        if (engineeringOrganizationUser.getParentId().equals("")) { //判断为是根节点
            log.info("---:" + userId.getUserId());
            //删除当前用户
            userOrganizationMapper.deleteBatch(userId.getUserId());
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        //当用户选择的是非根节点时,修改用户组织
        userOrganizationMapper.deleteBatchNotRootNote(userId.getUserId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }
 /*-------------------------------项目配置 | 角色-------------------------------*/








}

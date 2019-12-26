package com.bimforest.ems.modules.base.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.constant.CommonConstant;
import com.bimforest.ems.common.enums.MessageEnums;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.modules.base.entity.BaseOrganization;
import com.bimforest.ems.modules.base.service.BaseEngineeringRanageService;
import com.bimforest.ems.modules.base.service.BaseEngineeringService;
import com.bimforest.ems.modules.base.service.BaseOrganizationService;
import com.bimforest.ems.modules.base.service.BaseSubcontractorService;
import com.bimforest.ems.modules.construction.vo.WengineeringBaseUpdateVO;
import com.bimforest.ems.modules.construction.entity.WengineeringBaseInfo;
import com.bimforest.ems.modules.construction.service.WengineeringBaseInfoService;
import com.bimforest.ems.modules.base.entity.BaseEngineering;
import com.bimforest.ems.modules.sys.service.*;
import com.bimforest.ems.modules.sys.vo.EngineeringAddVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 项目管理表 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-12
 */
@Slf4j
@Api(description = "项目管理")
@RestController
@RequestMapping("/api/base/engineering-manager")
public class BaseEngineeringController {
    @Autowired
    BaseEngineeringService baseEngineeringService;
    @Autowired
    BaseEngineeringRanageService baseEngineeringRanageService;
    @Autowired
    BaseOrganizationService baseOrganizationService;
    @Autowired
    BaseSubcontractorService baseSubcontractorService;
    @Autowired
    SysUserService sysUserService;
    @Autowired
    SysEngineeringSubcontractService subcontractService;
    @Autowired
    BaseOrganizationService organization;
    @Autowired
    WengineeringBaseInfoService wengineeringBaseInfoService;

    @ApiOperation(value = "查询项目列表")
    @GetMapping("/selectList")
    public CommonResponse selectList(String engineeringName,
                                     @RequestParam(required = false, defaultValue = "10") @ApiParam("每页显示条数") int pageSize,
                                     @RequestParam(required = false, defaultValue = "1") @ApiParam("当前页码") int pageNum) {

        Page page = new Page(pageNum, pageSize);

        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseEngineeringService.selectEngineeringList(page, engineeringName));
    }

    @ApiOperation(value = "查询所有工程")
    @GetMapping("/all")
    public CommonResponse all(String engineeringName) {
        List<BaseEngineering> baseEngineerings = baseEngineeringService.list(new QueryWrapper<BaseEngineering>()
                .eq("status", StatusEnums.STATUS_VALID.getCode())
                .like(StrUtil.isNotBlank(engineeringName), "engineering_name", engineeringName)
        );
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseEngineerings);
    }

    @OperationLog(module = "项目管理", action = "新增项目")
    @ApiOperation(value = "新增项目", notes = "当用户勾选项目范围--施工 时 flag需传任意参数，当传参数后会自动创建 ," +
            "阶段名称（‘1’ 设计 ‘2’ bim ‘3’ 深化-钢构 ‘4’ 深化-PC ‘5’ 制造钢构 ‘6’ 制造PC ‘7’ 制造门窗 ‘8’ 施工）," +
            "项目所处阶段（‘1’ 未开始 2‘进行中’ 3 ‘已完成’）")
    @PostMapping("/insertEngineering")
    public CommonResponse insertEngineering(@RequestBody EngineeringAddVO engineeringAddVO) {

        //校验项目编码及项目名称
        if (engineeringAddVO.getEngineeringName() != "" && engineeringAddVO.getEngineeringCode() != "") {
            int i = baseEngineeringService.selectEngineeringCode(engineeringAddVO.getEngineeringCode());
            if (i > 0) {
                return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2014.getCode(), MessageEnums.FIAL_SYS_2014.getMessage());
            }
        } else {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2015.getCode(), MessageEnums.FIAL_SYS_2015.getMessage());
        }
        //创建组织机构----
        if (StringUtils.isNotEmpty(engineeringAddVO.getFlag())) {
            BaseOrganization baseOrganization = new BaseOrganization();
            baseOrganization.setName(engineeringAddVO.getEngineeringName());
            baseOrganization.setManagerId(engineeringAddVO.getManagerId());
            baseOrganization.setType(CommonConstant.DEPARTMENT);
            baseOrganizationService.save(baseOrganization);

            String organizationId = baseOrganization.getId();
            engineeringAddVO.setOrgId(organizationId);
        }
        //插入项目基本信息
        baseEngineeringService.insertBaseInfo(engineeringAddVO);

        //插入多条项目范围信息
        baseEngineeringRanageService.saveBatch(engineeringAddVO.getRangeList());
        //通过项目编码获取当前项目ID
        BaseEngineering id = baseEngineeringService.selectId(engineeringAddVO.getEngineeringCode());
        id.getId();
        //插入关联分包商信息
        baseSubcontractorService.saveBatch(engineeringAddVO.getSubcontractList());
        //修改范围关联对象
        baseEngineeringRanageService.updateMamagerId(id.getId(), id.getEngineeringCode());
        //修改分包商关联对象
        baseSubcontractorService.updateMamagerId(id.getId(), id.getEngineeringCode());
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @OperationLog(module = "项目管理", action = "修改项目")
    @ApiOperation(value = "修改项目")
    @PostMapping("/updateEngineering")
    public CommonResponse updateEngineering(@RequestBody EngineeringAddVO engineeringAddVO) {
        //校验项目编码
        if (engineeringAddVO.getEngineeringName() != null | engineeringAddVO.getEngineeringCode() != null) {
            int i = baseEngineeringService.selectOtherEngineeringCode(engineeringAddVO.getId(), engineeringAddVO.getEngineeringCode());
            if (i > 0) {
                return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2014.getCode(), MessageEnums.FIAL_SYS_2014.getMessage());
            }
        } else {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2015.getCode(), MessageEnums.FIAL_SYS_2015.getMessage());
        }
        //修改项目基本信息
        BaseEngineering engineeringManager = new BaseEngineering();
        engineeringManager.setId(engineeringAddVO.getId());
        engineeringManager.setEngineeringCode(engineeringAddVO.getEngineeringCode());
        engineeringManager.setEngineeringName(engineeringAddVO.getEngineeringName());
        engineeringManager.setEngineeringAbbreviation(engineeringAddVO.getEngineeringAbbreviation());
        engineeringManager.setContractPrice(engineeringAddVO.getContentPrice());
        engineeringManager.setManagerId(engineeringAddVO.getManagerId());
        baseEngineeringService.updateById(engineeringManager);
        //删除之前的项目范围数据
        baseEngineeringRanageService.delete(engineeringAddVO.getId());
        //插入多条项目范围信息
        baseEngineeringRanageService.saveBatch(engineeringAddVO.getRangeList());
        //创建组织机构---getFlag = null 删除 已经存在的 组织    getFlag != null 创建组织
        if (engineeringAddVO.getFlag().equals("")) {
            //查询关联组织ID
            BaseEngineering manager = baseEngineeringService.selectOne(engineeringManager.getId());
            //删除组织
            baseOrganizationService.deleteById(manager.getOrgId());
            //修改关联组织为""空字符串
            baseEngineeringService.updateOrgIdById(engineeringManager.getId());


        } else {
            //创建组织
            BaseOrganization baseOrganization = new BaseOrganization();
            baseOrganization.setName(engineeringAddVO.getEngineeringName());
            baseOrganization.setManagerId(engineeringAddVO.getManagerId());
            baseOrganization.setType(CommonConstant.DEPARTMENT);
            baseOrganizationService.save(baseOrganization);
            String organizationId = baseOrganization.getId();
            engineeringManager.setOrgId(organizationId);

        }
        //更新基本信息
        baseEngineeringService.updateById(engineeringManager);
        //删除之前的分包商信息数据
        baseSubcontractorService.delete(engineeringAddVO.getId());
        //插入关联分包商信息
        baseSubcontractorService.saveBatch(engineeringAddVO.getSubcontractList());
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation("管理员选择")
    @GetMapping("/selectUserListByNamePhone")
    public CommonResponse selectUserList() {
        List list = sysUserService.selectUserList();
        return new CommonResponse(ResponseTypeEnums.SUCCESS, list);
    }

    @ApiOperation("分包商选择")
    @GetMapping("/selectSubcontractList")
    public CommonResponse selectSubcontractList() {
        List list = subcontractService.selectSubcontractList();
        return new CommonResponse(ResponseTypeEnums.SUCCESS, list);
    }

    @ApiOperation("根据组织id获取工程信息")
    @GetMapping("/getEngineeringByOrgId")
    public CommonResponse getEngineeringByOrgId(String orgId) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS,organization.selectBaseInfoOne(orgId));
    }

    @ApiOperation("根据组织id更新工程信息")
    @PostMapping("/updateEngineeringByOrgId")
    public CommonResponse updateEngineeringByOrgId(@RequestBody WengineeringBaseUpdateVO wengineeringBaseUpdateVO) {
        WengineeringBaseInfo wengineeringBaseInfo = new WengineeringBaseInfo();
        BeanUtils.copyProperties(wengineeringBaseUpdateVO, wengineeringBaseInfo);
        boolean flag = wengineeringBaseInfoService.update(wengineeringBaseInfo, new QueryWrapper<WengineeringBaseInfo>()
                .eq("org_id", wengineeringBaseUpdateVO.getOrgId()));

        return new CommonResponse(ResponseTypeEnums.SUCCESS, flag);
    }

}

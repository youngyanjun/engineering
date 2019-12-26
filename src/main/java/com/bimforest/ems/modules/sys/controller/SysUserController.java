package com.bimforest.ems.modules.sys.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.constant.CommonConstant;
import com.bimforest.ems.common.enums.DefaultEnums;
import com.bimforest.ems.common.enums.MessageEnums;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.common.utils.RandomNumUtils;
import com.bimforest.ems.modules.base.entity.BaseOrganization;
import com.bimforest.ems.modules.base.service.BaseOrganizationService;
import com.bimforest.ems.modules.sys.entity.SysRole;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.service.SysRoleService;
import com.bimforest.ems.modules.sys.service.SysUserService;
import com.bimforest.ems.modules.sys.vo.SysUserVO;
import com.bimforest.ems.modules.sys.vo.UserUpdateVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-10-30
 */
@Slf4j
@Api(description = "人员管理")
@RestController
@RequestMapping("/api/sys/sys-user")
public class SysUserController {

    @Autowired
    SysUserService sysUserService;
    @Autowired
    BaseOrganizationService baseOrganizationService;
    @Autowired
    SysRoleService sysRoleService;

    @ApiOperation(value = "人员管理 ", notes = "status ‘1’ 用户状态正常， ‘2’ 用户 冻结")
    @GetMapping("/userList")
    public CommonResponse selectUserList(String userName,
                                         @RequestParam(required = false, defaultValue = "10") @ApiParam("每页显示条数") int pageSize,
                                         @RequestParam(required = false, defaultValue = "1") @ApiParam("当前页码") int pageNum) {
        Page page = new Page(pageNum, pageSize);
        IPage<SysUserVO> userListVOIPage = sysUserService.selectList(page, userName);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, userListVOIPage);
    }

    @OperationLog(module = "人员管理", action = "新建用户")
    @ApiOperation("新建用户")
    @GetMapping("/insertUser")
    public CommonResponse insertUser(String userName, String email, String phone) {
        boolean checkPhoneEMail = sysUserService.checkPhoneEMail(email, phone);
        //设置默认密码
        String password = DefaultEnums.DEFAULT_PASSWORD.getCode();

        //判断邮箱和手机是否符合数据规则
        if (checkPhoneEMail) {
            //判断用户输入的邮箱和手机号是否已经存在
            boolean queryPhoneEmail = sysUserService.queryPhoneEmail(email, phone);
            if (queryPhoneEmail) {
                return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2001.getCode(), MessageEnums.FIAL_SYS_2001.getMessage());
            }
            //生成随机盐
            String salt = RandomNumUtils.randomNumberGenerator.nextBytes().toHex();
            SysUser sysUser = new SysUser();
            sysUser.setUserName(userName);
            sysUser.setEMail(email);
            sysUser.setPhone(phone);
            sysUser.setUserType(CommonConstant.USER_TYPE_ORDINARY);
            sysUser.setSalt(salt);
            //加密后的新密码
            String newPassword =
                    new SimpleHash(CommonConstant.ENCRYPTION_ALGORITHM, CommonConstant.DEFAULT_PASSWORD,
                            ByteSource.Util.bytes(salt), CommonConstant.ENCRYPTION_NUMBER).toHex();
            sysUser.setUserPwd(newPassword);
            sysUserService.save(sysUser);

            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2009.getCode(), MessageEnums.FIAL_SYS_2009.getMessage());

    }

    @OperationLog(module = "人员管理", action = "冻结账号")
    @ApiOperation(value = "人员管理---批量冻结账号")
    @GetMapping("/updateAccountDisableBatch")
    public CommonResponse updateAccountDisableBatch(@ApiParam("需要批量更新（冻结账号）的用户ID") @RequestParam(value = "ids") List<String> ids) {
        int status = StatusEnums.DISABLE.getCode();
        int i = sysUserService.updateStatus(ids, status);
        if (i < 1) {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @OperationLog(module = "人员管理", action = "解冻账号")
    @ApiOperation(value = "人员管理---批量解冻账号")
    @GetMapping("/updateAccountEnableBatch")
    public CommonResponse updateAccountEnableBatch(@ApiParam("需要批量更新（解冻账号）的用户ID") @RequestParam(value = "ids") List<String> ids) {
        int status = StatusEnums.ENABLED_Y.getCode();
        int i = sysUserService.updateStatus(ids, status);
        if (i < 1) {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @OperationLog(module = "人员管理", action = "删除用户")
    @ApiOperation(value = "人员管理---删除用户")
    @GetMapping("/deleteAccount")
    public CommonResponse deleteAccount(@ApiParam("需要批量更新（删除用户）的用户ID") @RequestParam(value = "ids") List<String> ids) {
        //获取删除账号标识
        int status = StatusEnums.ENABLED_N.getCode();
        int i = sysUserService.updateStatus(ids, status);
        if (i < 1) {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @OperationLog(module = "人员管理", action = "重置账号")
    @ApiOperation("重置账号")
    @GetMapping("/updatePassword")
    public CommonResponse updatePassword(@RequestParam(value = "ids") List<String> ids) {
        //获取用户默认密码
        String password = DefaultEnums.DEFAULT_PASSWORD.getCode();
        int i = sysUserService.updateBatchUserPwd(ids);
        if (i == ids.size()) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        //重置选中用户密码
        return new CommonResponse(ResponseTypeEnums.FAILED);
    }

    @OperationLog(module = "人员管理", action = "编辑用户")
    @ApiOperation(value = "编辑用户", notes = "fieldsSum ,status ,phone 应为空,")
    @GetMapping("/updateAccountOne")
    public CommonResponse updateAccountOne(String id, String email, String userName) {
        //判断邮箱是否符合规则
        boolean checkPhoneEMail = sysUserService.checkEMail(email);
        if (!checkPhoneEMail) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2009.getCode(), MessageEnums.FIAL_SYS_2009.getMessage());
        }

        //判断邮箱是否已被占用
        int checkEmailIsEnable = sysUserService.checkEmailIsEnable(email, id);
        if (checkEmailIsEnable > 0) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2012.getCode(), MessageEnums.FIAL_SYS_2012.getMessage());
        }
        SysUser sysUser = new SysUser();
        sysUser.setId(id);
        sysUser.setUserName(userName);
        sysUser.setEMail(email);
        sysUserService.updateById(sysUser);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation("查询用户所有权限-及用户所有角色信息")
    @GetMapping("/selectUserEngineering")
    public CommonResponse selectUserEngineering(String id) {
        sysUserService.selectUserRoleMenu(id);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation("WEB前端查询单个用户信息")
    @GetMapping("/selectUserInfoOne")
    public CommonResponse selectUserInfoOne(String phone) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, sysUserService.selectUserEngineering(phone));
    }

    @OperationLog(module = "个人中心", action = "修改密码")
    @ApiOperation("用户修改密码")
    @GetMapping("/updateUserPwd")
    public CommonResponse updateUserPwd(String id, String oldPwd, String newPwd) {
        int i = sysUserService.updateUserPwd(id, oldPwd, newPwd);
        if (i > 0) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2016.getCode(), MessageEnums.FIAL_SYS_2016.getMessage());
    }

    @OperationLog(module = "个人中心", action = "修改邮箱")
    @ApiOperation("用户编辑邮箱")
    @GetMapping("/updateUserEmail")
    public CommonResponse updateUserEmail(String email, String id) {
        //检查用户输入邮箱是否符合规则
        if (sysUserService.checkEMail(email)) {
            //邮箱是否已经存在
            int i = sysUserService.selectOneEmail(email, id);
            if (i == 0) {
                SysUser sysUser = new SysUser();
                sysUser.setId(id);
                sysUser.setEMail(email);
                sysUserService.updateById(sysUser);
                return new CommonResponse(ResponseTypeEnums.SUCCESS);
            }
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2012.getCode(), MessageEnums.FIAL_SYS_2012.getMessage());
        }
        return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2011.getCode(), MessageEnums.FIAL_SYS_2011.getMessage());
    }

    @ApiOperation("获取登录用户信息")
    @GetMapping("/getLoginUserInfoByOrgId")
    public CommonResponse getLoginUserInfoByOrgId(String orgId) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        Map<String, Object> map = new HashMap<>();
        if(StrUtil.isNotBlank(orgId)){
            BaseOrganization organization = baseOrganizationService.getById(orgId);
            // 用户所在的组织信息
            map.put("organization", organization);

            List<SysRole> sysRoles = sysRoleService.selectUserRolesByOrgId(user.getId(), orgId);
            // 用户所拥有的角色
            map.put("sysRoles", sysRoles);
        }
        SysUser sysUser = sysUserService.getById(user.getId());
        // 用户信息
        map.put("sysUser", sysUser);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, map);
    }

    @ApiOperation("校验用户密码是否正确")
    @GetMapping("/checkUserPassWord")
    public CommonResponse checkUserPassWord(String pwd) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        boolean flag = sysUserService.checkUserPassWord(user.getId(),pwd);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, flag);
    }


    @OperationLog(module = "个人中心", action = "修改用户信息")
    @ApiOperation(value = "个人中心修改个人信息")
    @PostMapping("/updateLoginUserInfo")
    public CommonResponse updateLoginUserInfo(@RequestBody UserUpdateVO userUpdateVO) {
        //判断邮箱是否符合规则
        boolean checkPhoneEMail = sysUserService.checkEMail(userUpdateVO.getEMail());
        if (!checkPhoneEMail) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2009.getCode(), MessageEnums.FIAL_SYS_2009.getMessage());
        }

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        //判断邮箱是否已被占用
        int checkEmailIsEnable = sysUserService.checkEmailIsEnable(userUpdateVO.getEMail(), user.getId());
        if (checkEmailIsEnable > 0) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2012.getCode(), MessageEnums.FIAL_SYS_2012.getMessage());
        }

        int i = sysUserService.updateUserPwd(user.getId(), userUpdateVO.getUserPwd(), userUpdateVO.getNewUserPwd());
        if (i <= 0) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2016.getCode(), MessageEnums.FIAL_SYS_2016.getMessage());
        }

        SysUser sysUser = new SysUser();
        sysUser.setId(user.getId());
        sysUser.setUserName(userUpdateVO.getUserName());
        sysUser.setEMail(userUpdateVO.getEMail());
        sysUserService.updateById(sysUser);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }
}
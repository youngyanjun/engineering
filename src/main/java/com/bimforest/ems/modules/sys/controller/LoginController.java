package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.bean.CacheUser;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.constant.CommonConstant;
import com.bimforest.ems.common.enums.MessageEnums;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.log.entity.SysLog;
import com.bimforest.ems.modules.log.mapper.SysLogMapper;
import com.bimforest.ems.modules.sys.service.SysUserService;
import com.bimforest.ems.modules.sys.vo.LoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.Around;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.logging.LogManager;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-11
 */
@Slf4j
@Api(description = "登录-登出")
@RestController
@RequestMapping("/api/sys/")
public class LoginController {
    @Autowired
    SysUserService sysUserService;

    @Autowired
    SysLogMapper sysLogMapper;
    @OperationLog(module = "登陆" ,action = "登陆")
    @ApiOperation(value = "登录")
    @PostMapping("/login")
    public CommonResponse login(@RequestBody LoginVO longin) {

        if (StringUtils.isBlank(longin.getPhone())) {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }

        if (StringUtils.isBlank(longin.getPassword())) {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        //获取用户类型

        CacheUser loginUser = sysUserService.login(longin.getPhone(), longin.getPassword());
        // 登录成功返回用户信息
        if (loginUser == null ) {

            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2003.getCode(), MessageEnums.FIAL_SYS_2003.getMessage());
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS, loginUser);
    }

    @ApiOperation("登出")
    @GetMapping("/logout")
    public CommonResponse logout() {
        sysUserService.userLogout();
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }



}



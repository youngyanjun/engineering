package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.service.SysMessageService;
import com.bimforest.ems.modules.sys.vo.SysMessageIdsVO;
import com.bimforest.ems.modules.sys.vo.SysMessageSelectVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-17
 */

@Api(description = "系统消息")
@RestController
@RequestMapping("/api/sys/message")
public class SysMessageController {

    @Autowired
    SysMessageService sysMessageService;

    @ApiOperation(value = "查询登录用户的消息")
    @GetMapping("/selectSysMessagesForLoginUser")
    public CommonResponse selectSysMessagesForLoginUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<SysMessageSelectVO> sysMessageSelectVOS = sysMessageService.selectSysMessagesByUserId(user.getId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, sysMessageSelectVOS);
    }

    @ApiOperation(value = "查询登录用户的未读消息数量")
    @GetMapping("/selectUnReadMessagesForLoginUser")
    public CommonResponse selectUnReadMessagesForLoginUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int amount = sysMessageService.selectUnReadMessageAmountForLoginUser(user.getId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, amount);
    }

    @ApiOperation(value = "根据消息id查询消息")
    @GetMapping("/getSysMessageById")
    public CommonResponse getSysMessageById(String id) {
        SysMessageSelectVO sysMessageSelectVO = sysMessageService.getSysMessageById(id);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, sysMessageSelectVO);
    }


    @ApiOperation(value = "更新登录用户的消息为已读")
    @PostMapping("/updateSysMessagesForLoginUser")
    public CommonResponse updateSysMessagesForLoginUser(@RequestBody SysMessageIdsVO sysMessageIdsVO) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int record = sysMessageService.updateReadFlag(sysMessageIdsVO.getMessageIds());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, record);
    }

    @ApiOperation(value = "删除登录用户的消息")
    @PostMapping("/deleteSysMessagesForLoginUser")
    public CommonResponse deleteSysMessagesForLoginUser(@RequestBody SysMessageIdsVO sysMessageIdsVO) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        int record = sysMessageService.delete(sysMessageIdsVO.getMessageIds(), user.getId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, record);
    }

}

package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.service.SysMenuService;
import com.bimforest.ems.modules.sys.vo.MenuListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Description: 菜单列表
* @author max.zhang
* @date 2019/12/12 13:55
*/
@Slf4j
@Api(description = "默认权限菜单列表")
@RestController
@RequestMapping("/api/sys/menu")
public class SysMenuController {

    @Autowired
    SysMenuService menuService;

    @ApiOperation("默认权限菜单列表")
    @GetMapping("/selectMenuList")
    public CommonResponse selectMenuList(String id) {

        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        String phone = user.getPhone();


        List<MenuListVO> menuListVOS = menuService.selectMenuList(id);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,menuListVOS);
    }

}

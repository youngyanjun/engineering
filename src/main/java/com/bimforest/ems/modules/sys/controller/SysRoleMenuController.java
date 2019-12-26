package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.sys.entity.SysRoleMenu;
import com.bimforest.ems.modules.sys.service.SysRoleMenuService;
import com.bimforest.ems.modules.sys.vo.UpdateRoleMenuVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
* @Description: 角色菜单关联
* @return
* @author max.zhang
* @date 2019/12/12 13:57
*/
@Api(description = "角色菜单管理")
@RestController
@RequestMapping("/api/sys/role-menu")
public class SysRoleMenuController {
    @Autowired
    SysRoleMenuService roleMenuService;

    @OperationLog(module = "默认项目部组织配置",action = "修改角色权限")
    @ApiOperation("权限可用资源授权修改")
    @PostMapping("/updateRoleMenu")
    public CommonResponse updateRoleMenu(@RequestBody UpdateRoleMenuVO roleMenuVo ){
        List<SysRoleMenu> list = roleMenuVo.getList();
        //删除之前权限
        roleMenuService.deleteByRoleId(list.get(0).getRoleId().toString());
        //重新建立新的权限
        boolean insertRoleMenu = roleMenuService.saveBatch(list);
        if(!insertRoleMenu){
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

}

package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.sys.service.SysRoleService;
import com.bimforest.ems.modules.sys.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author max.zhang
 * @Description: 角色列表
 * @date 2019/12/12 13:56
 */
@Api(description = "角色管理")
@RestController
@RequestMapping("/api/sys/role")
public class SysRoleController {
    @Autowired
    SysRoleService roleService;


    @ApiOperation("角色列表")
    @GetMapping("selectRoleTemplateList")
    public CommonResponse selectRoleTemplateList() {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, roleService.RoleTreeList());
    }

    @ApiOperation("角色列表新")
    @GetMapping("selectRoles")
    public CommonResponse selectRoles(String orgId) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, roleService.selectRoles(orgId));
    }

    @OperationLog(module = "新增角色", action = "角色添加")
    @ApiOperation("新增角色")
    @PostMapping("addRole")
    public CommonResponse addRole(@RequestBody RoleAddVO roleAddVO) {
        int record = roleService.addRole(roleAddVO.getRoleName(), roleAddVO.getRemark(), roleAddVO.getParentId(), roleAddVO.getOrgId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, record);
    }

    @OperationLog(module = "默认项目部组织配置", action = "角色添加")
    @ApiOperation("角色添加")
    @GetMapping("insertRoleTemplateList")
    public CommonResponse insertRoleTemplateList(String name, String remark, String parentId) {

        int i = roleService.addRole(name, remark, parentId, null);
        if (i > 0) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED);
    }

    @OperationLog(module = "默认项目部组织配置", action = "角色修改")
    @ApiOperation("角色修改")
    @GetMapping("updateRoleTemplateList")
    public CommonResponse updateRoleTemplateList(String name, String remark, String id) {
        int i = roleService.updateRoleTemplateList(name, remark, id);
        if (i > 0) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED);

    }

    @OperationLog(module = "角色修改", action = "角色修改")
    @ApiOperation("角色修改新")
    @PostMapping("updateRole")
    public CommonResponse updateRole(@RequestBody RoleUpdateVO roleUpdateVO) {
        int record = roleService.updateRole(roleUpdateVO);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, record);

    }

    @OperationLog(module = "默认项目部组织配置", action = "角色删除")
    @ApiOperation("角色删除")
    @GetMapping("deleteRoleTemplateList")
    public CommonResponse deleteRoleTemplateList(String id) {
        int i = roleService.deleteRoleTemplateList(id);
        if (i > 0) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED);
    }

    @OperationLog(module = "角色删除新", action = "角色删除")
    @ApiOperation("角色删除")
    @PostMapping("deleteRole")
    public CommonResponse deleteRole(@RequestBody RoleDeleteVO roleDeleteVO) {
        Integer record = roleService.deleteRole(roleDeleteVO.getId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, record);
    }

    @OperationLog(module = "为角色添加用户", action = "为角色添加用户")
    @ApiOperation("为角色添加用户")
    @PostMapping("addUsersForRole")
    public CommonResponse addUsersForRole(@RequestBody RoleUserAddVO roleUserAddVO) {
        int record = roleService.addUsersForRole(roleUserAddVO);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, record);
    }

    @ApiOperation("根据角色id查询角色下的用户")
    @GetMapping("selectUsersForRole")
    public CommonResponse selectUsersForRole(String orgId, String roleId) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, roleService.selectUsersForRole(orgId, roleId));
    }

    @ApiOperation("删除角色下的人员")
    @PostMapping("deleteUsersForRole")
    public CommonResponse deleteUsersForRole(@RequestBody RoleUserDeleteVO roleUserDeleteVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, roleService.deleteUsersForRole(roleUserDeleteVO));
    }

    @ApiOperation("角色添加人员下拉列表")
    @GetMapping("selectUsersNotInRole")
    public CommonResponse selectUsersNotInRole(String roleId, String userName) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, roleService.selectUsersNotInRole(roleId, userName));
    }


}

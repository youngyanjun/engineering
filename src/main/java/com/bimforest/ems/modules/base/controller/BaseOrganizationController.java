package com.bimforest.ems.modules.base.controller;


import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.base.entity.BaseOrganization;
import com.bimforest.ems.modules.base.service.BaseOrganizationService;
import com.bimforest.ems.modules.sys.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 组织 前端控制器
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-16
 */
@Api(description = "组织")
@RestController
@RequestMapping("/api/base/organization")
public class BaseOrganizationController {

    @Autowired
    BaseOrganizationService organizationService;

    @Autowired
    com.bimforest.ems.modules.base.service.BaseOrganizationService baseOrganizationService;

    @ApiOperation("查询登录用户拥有的组织")
    @GetMapping("/selectOrganizationsForLoginUser")
    public CommonResponse selectOrganizationsForLoginUser() {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        List<BaseOrganization> baseOrganizations = baseOrganizationService.selectOrganizationsByUserId(user.getId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseOrganizations);
    }

   

    @ApiOperation("组织机构列表")
    @GetMapping("/selectOrganizationList")
    public CommonResponse selectOrganizationList(String name) {
        organizationService.selectOrganizationList(name);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, organizationService.selectOrganizationList(name));
    }

    @OperationLog(module = "组织管理", action = "编辑组织")
    @ApiOperation("编辑组织机构")
    @GetMapping("/updateOrganizationList")
    public CommonResponse updateOrganizationList(String id, String name, String managerId, String remark) {
       BaseOrganization organization = new BaseOrganization();
        organization.setId(id);
        organization.setName(name);
        organization.setManagerId(managerId);
        organization.setRemark(remark);
        boolean byId = organizationService.updateById(organization);
        if (!byId) {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }


    @OperationLog(module = "组织管理", action = "修改项目地址")
    @ApiOperation("编辑组织机构")
    @GetMapping("/updateAddress")
    public CommonResponse updateAddress(String id, String longitude, String latitude) {
        organizationService.updateAddress(id, longitude, latitude);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    } 
    
    
}

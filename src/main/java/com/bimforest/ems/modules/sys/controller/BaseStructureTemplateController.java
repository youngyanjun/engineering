package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.base.entity.BaseStructure;
import com.bimforest.ems.modules.base.mapper.BaseStructureMapper;
import com.bimforest.ems.modules.sys.entity.EngineeringOrganizationTemplate;
import com.bimforest.ems.modules.sys.service.EngineeringOrganizationTemplateService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.naming.Name;
import javax.xml.crypto.Data;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
@Api(description = "默认组织列表")
@RestController
@RequestMapping("/api/sys/engineering-organization-template")
public class BaseStructureTemplateController {
    @Autowired
    EngineeringOrganizationTemplateService templateService;

    @Autowired
    BaseStructureMapper baseStructureMapper;
    @ApiOperation("默认组织列表")
    @GetMapping("/selectOrganizationTemplateList")
    public CommonResponse selectOrganizationTemplateList() {
        List list = templateService.selectOrganizationTemplateList();
        return new CommonResponse(ResponseTypeEnums.SUCCESS, list);
    }
    @ApiOperation("添加默认组织")
    @OperationLog(module = "默认项目部组织配置",action = "添加默认组织")
    @GetMapping("/insertOrganizationTemplateList")
    public CommonResponse insertOrganizationTemplateList(String parentId, String name, String remark) {
        BaseStructure organizationTemplate = new BaseStructure();
        organizationTemplate.setStructureName(name);
        organizationTemplate.setRemark(remark);
        organizationTemplate.setParentId(parentId);
        int save = baseStructureMapper.insert(organizationTemplate);
        if (save > 0 ) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED);
    }
    @ApiOperation("编辑默认组织")
    @OperationLog(module = "默认项目部组织配置",action = "编辑默认组织")
    @GetMapping("/updateOrganizationTemplateList")
    public CommonResponse updateOrganizationTemplateList(String id, String name, String remark) {
        BaseStructure organizationTemplate = new BaseStructure();
        organizationTemplate.setId(id);
        organizationTemplate.setStructureName(name);
        organizationTemplate.setRemark(remark);
        int save = baseStructureMapper.updateById(organizationTemplate);
        if (save > 0 ) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED);
    }
    @ApiOperation("删除默认组织")
    @OperationLog(module = "默认项目部组织配置",action = "删除默认组织")
    @GetMapping("/deleteOrganizationTemplateList")
    public CommonResponse deleteOrganizationTemplateList(String id) {
        BaseStructure organizationTemplate = new BaseStructure();
        organizationTemplate.setId(id);

        int delete = baseStructureMapper.deleteById(id);
        if (delete > 0) {
            return new CommonResponse(ResponseTypeEnums.SUCCESS);
        }
        return new CommonResponse(ResponseTypeEnums.FAILED);
    }
}

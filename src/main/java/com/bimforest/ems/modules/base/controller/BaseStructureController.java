package com.bimforest.ems.modules.base.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.base.vo.*;
import com.bimforest.ems.modules.base.service.BaseStructureService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-13
 */
@Api(description = "组织结构")
@RestController
@RequestMapping("/api/base/structure")
public class BaseStructureController {

    @Autowired
    BaseStructureService baseStructureService;

    @ApiOperation("查询组织结构树")
    @GetMapping("selectStructureTree")
    public CommonResponse selectStructures(String orgId) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.selectStructureTree(orgId));
    }

    @ApiOperation("新增组织结构")
    @PostMapping("addStructure")
    public CommonResponse addStructure(@RequestBody StructureAddVO structureAddVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.addStructure(structureAddVO));
    }

    @ApiOperation("修改组织结构")
    @PostMapping("updateStructure")
    public CommonResponse updateStructure(@RequestBody StructureAddVO structureAddVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.updateStructure(structureAddVO));
    }

    @ApiOperation("删除组织结构")
    @PostMapping("deleteStructure")
    public CommonResponse deleteStructure(@RequestBody StructureDeleteVO structureDeleteVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.deleteStructure(structureDeleteVO.getId()));
    }

    @ApiOperation("为组织结构新增人员")
    @PostMapping("addUsersForStructure")
    public CommonResponse addUsersForStructure(@RequestBody UserStructureAddVO userStructureAddVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.addUsersForStructure(userStructureAddVO));
    }

    @ApiOperation("组织结构添加人员下拉列表")
    @GetMapping("selectUsersNotInStructure")
    public CommonResponse selectUsersNotInStructure(String structureId, String userName) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.selectUsersNotInStructure(structureId, userName));
    }

    @ApiOperation("删除组织结构下的人员")
    @PostMapping("deleteUsersForStructure")
    public CommonResponse deleteUsersForStructure(@RequestBody StructureUserDeleteVO structureUserDeleteVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.deleteUsersForStructure(structureUserDeleteVO));
    }

    @ApiOperation("根据组织结构id查询用户")
    @GetMapping("selectUsersByStructureId")
    public CommonResponse selectUsersByStructureId(String structureId) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.selectUsersByStructureId(structureId));
    }

    @ApiOperation("根据组织id查询用户")
    @GetMapping("selectUsersByOrgId")
    public CommonResponse selectUsersByOrgId(String orgId) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.selectUsersByOrgId(orgId));
    }

    @ApiOperation("删除组织下的人员")
    @PostMapping("deleteUsersForOrg")
    public CommonResponse deleteUsersForOrg(@RequestBody OrgUserDeleteVO orgUserDeleteVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseStructureService.deleteUsersForOrg(orgUserDeleteVO));
    }

}

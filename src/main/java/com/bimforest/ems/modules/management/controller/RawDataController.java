package com.bimforest.ems.modules.management.controller;

import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.base.service.BaseEngineeringService;
import com.bimforest.ems.modules.department.service.ReportListService;
import com.bimforest.ems.modules.management.VO.SelectRawDataListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 项管中心原始数据
 *
 * @author youngyanjun
 * @date 2019-12-23 10:43
 */
@Api(description = "原始数据")
@RestController
@RequestMapping("/api/web/raw")
public class RawDataController {

    @Autowired
    ReportListService reportListService;
    @Autowired
    BaseEngineeringService engineeringManagerService;

    @ApiOperation("获取原始数据列表")
    @PostMapping("getRawDataList")
    public CommonResponse getRawDataList(@RequestBody SelectRawDataListVO rawDataList) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS,reportListService.getRawDataList(rawDataList));
    }

    /*@ApiOperation("获取项目list")
    @PostMapping("getEngList")
    public CommonResponse getEngOrgList() {
        return new CommonResponse(ResponseTypeEnums.SUCCESS,engineeringManagerService.getEngList());
    }

    @ApiOperation("获取公司list")
    @PostMapping("getOrgList")
    public CommonResponse getOrgList() {
        return new CommonResponse(ResponseTypeEnums.SUCCESS,engineeringManagerService.getOrgList());
    }*/

}

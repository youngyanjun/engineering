package com.bimforest.ems.modules.base.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.base.service.BaseSubcontractorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
@Api(description="项目关联分包商")
@RestController
@RequestMapping("/api/base/engineering-involve-subcontract")
public class BaseEngineeringSubcontractorController {
    @Autowired
    BaseSubcontractorService subcontractService;


    @ApiOperation("项目关联分包商")
    @GetMapping("/selectSubcontractList")
    public CommonResponse selectSubcontractList(String engineeringId){

        return new CommonResponse(ResponseTypeEnums.SUCCESS,null,null,subcontractService.selectSubcontractList(engineeringId));
    }

}

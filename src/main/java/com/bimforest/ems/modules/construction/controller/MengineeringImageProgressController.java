package com.bimforest.ems.modules.construction.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.construction.vo.ImageProgressVO;
import com.bimforest.ems.modules.construction.vo.InsertImageProgressVO;
import com.bimforest.ems.modules.construction.service.MengineeringImageProgressService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
@RestController
@RequestMapping("/api/web/report/mengineering-image-progress/")
public class MengineeringImageProgressController {

    @Autowired
    MengineeringImageProgressService progressService;

    @ApiOperation(value = "插入月度形象表",notes = "month 为月次")
    @PostMapping("insetImageProgress")
    public CommonResponse insetImageProgress(InsertImageProgressVO imageProgress) {
        progressService.insetImageProgress(imageProgress);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);

    }

    @ApiOperation(value = "修改月度形象表",notes = "month 为月次, ")
    @PostMapping("updateImageProgress")
    public CommonResponse updateImageProgress(@RequestBody ImageProgressVO progress) {
        progressService.updateImageProgress(progress);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);

    }

    @ApiOperation(value = "获取当年未使用月度",notes = "organizationId 为组织ID, ")
    @PostMapping("selectMonthByEngineering")
    public CommonResponse selectMonthByEngineering(String organizationId) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS,progressService.selectMonthByEngineering(organizationId));

    }

    @ApiOperation(value = "获取单条月度形象信息",notes = "id 当前月度形象报表ID ")
    @PostMapping("selectOneImageProgress")
    public CommonResponse selectOneImageProgress(String id) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS,progressService.selectOneImageProgress(id));

    }


}

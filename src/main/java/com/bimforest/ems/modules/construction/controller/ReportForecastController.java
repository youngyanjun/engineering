package com.bimforest.ems.modules.construction.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.construction.vo.ReportForecastAddVO;
import com.bimforest.ems.modules.construction.vo.ReportForecastSelectVO;
import com.bimforest.ems.modules.construction.vo.ReportForecastVO;
import com.bimforest.ems.modules.construction.service.ReportForecastService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  周报-天气预报
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
@Api(description = "周报-天气预报")
@RestController
@RequestMapping("/report/report-forecast")
public class ReportForecastController {

    @Autowired
    private ReportForecastService reportForecastService;

    @ApiOperation("查询天气预报")
    @GetMapping("/queryForecast")
    public CommonResponse queryForecast(ReportForecastSelectVO reportForecastVO) {
        List<ReportForecastVO> reportForecasts = reportForecastService.queryReportForecast(reportForecastVO);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportForecasts);
    }

    @ApiOperation("更新天气预报")
    @PostMapping("/addForecast")
    public CommonResponse addForecast(@RequestBody ReportForecastAddVO reportForecastAddVO) {
        reportForecastService.addForecast(reportForecastAddVO);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, null);
    }

}

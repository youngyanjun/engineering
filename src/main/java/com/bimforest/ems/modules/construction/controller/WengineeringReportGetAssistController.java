package com.bimforest.ems.modules.construction.controller;

import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.construction.service.WengineeringBaseInfoService;
import com.bimforest.ems.modules.construction.service.WengineeringReportMachineService;
import com.bimforest.ems.modules.construction.service.WengineeringReportPersonnelService;
import com.bimforest.ems.modules.construction.service.WengineeringReportService;
import com.bimforest.ems.modules.sys.service.DictionaryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author youngyanjun
 * @date 2019-12-06 9:54
 */
@Slf4j
@Api(description = "工程周报填写辅助")
@RestController
@RequestMapping("/api/web/report/Assist")
public class WengineeringReportGetAssistController {
    @Autowired
    WengineeringReportService reportService;
    @Autowired
    WengineeringBaseInfoService baseInfoService;
    @Autowired
    WengineeringReportMachineService machineService;
    @Autowired
    WengineeringReportPersonnelService personnelService;
    @Autowired
    DictionaryService dictionaryService;





    @ApiOperation(value = "下周项目进度",notes = "参数 Weekly 为项目为周次")
    @GetMapping("getNextStageInfo")
    public CommonResponse getNextStageInfo(Integer weekly,String engineeringId){
        return new CommonResponse(ResponseTypeEnums.SUCCESS,reportService.getNextStageInfo(weekly,engineeringId));

    }
    @ApiOperation(value = "获取上周计划施工机械",notes = "参数 Weekly 为当前项目为周次")
    @GetMapping("getNextMachine")
    public CommonResponse getNextMachine(Integer weekly,String engineeringId){
        //获取对应周次的周报ID
        String id = reportService.getWeeklyIdByWeekly(weekly,engineeringId);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,machineService.machineList(id, "1"));

    }

    @ApiOperation(value = "下周施工人员",notes = "参数 Weekly为项目为周次")
    @GetMapping("getNextPerson")
    public CommonResponse getNextPerson(Integer weekly,String engineeringId){
        //获取对应周次的周报ID
        String id = reportService.getWeeklyIdByWeekly(weekly,engineeringId);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,personnelService.PersonnelList(id, "1"));

    }
    @ApiOperation(value = "自定义字段下拉",notes = "参数 id 为自定义字段id")
    @GetMapping("getDictionaryList")
    public CommonResponse getDictionaryList(@RequestParam String id){
        dictionaryService.selectOne(id);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,dictionaryService.selectOne(id));

    }
}

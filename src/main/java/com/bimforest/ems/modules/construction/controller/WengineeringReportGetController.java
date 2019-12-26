package com.bimforest.ems.modules.construction.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.common.enums.SysFileTypeEnums;
import com.bimforest.ems.common.utils.CheckObjectIsNullUtils;
import com.bimforest.ems.common.utils.WeeklyUtil;
import com.bimforest.ems.modules.construction.vo.MustInfoVO;
import com.bimforest.ems.modules.construction.vo.StageInfoVO;
import com.bimforest.ems.modules.construction.vo.WeeklyMustFiledVO;
import com.bimforest.ems.modules.construction.service.WengineeringReportMachineService;
import com.bimforest.ems.modules.construction.service.WengineeringReportPersonnelService;
import com.bimforest.ems.modules.construction.service.WengineeringReportService;
import com.bimforest.ems.modules.sys.entity.SysFile;
import com.bimforest.ems.modules.sys.service.EngineeringSystemWeeklyService;
import com.bimforest.ems.modules.sys.service.SysFileService;
import com.bimforest.ems.pojo.DateWeekly;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-28
 */
@Slf4j
@Api(description = "工程周报获取")
@RestController
@RequestMapping("/api/web/report/weeklyreport/get/")
public class WengineeringReportGetController {

    @Autowired
    WengineeringReportService reportService;
    @Autowired
    EngineeringSystemWeeklyService weeklyService;
    @Autowired
    SysFileService sysFileService;

    @Autowired
    WengineeringReportMachineService machineService;
    @Autowired
    WengineeringReportPersonnelService personnelService;


    /**
     * 获取信息--智能 提示辅助
     *
     * @return
     */

    @ApiOperation("获取周次")
    @GetMapping("getWeekly")
    public CommonResponse getWeekly(@RequestParam String id) {

        String startDate = weeklyService.selectList().get(0).getStartDate();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        String endDate = format.format(newDate);
        List<DateWeekly> weekly = WeeklyUtil.getWeekly(startDate, endDate);
        //查出本项目所有已经存在的周次list >周报日期在设置的日期范围之内的
        List<DateWeekly> wengineeringReports = reportService.selectListWeeklyById(id);
        //去除已经存在的周报
        weekly.removeAll(wengineeringReports);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, weekly);
    }

    @ApiOperation("获取基本信息")
    @GetMapping("selectBaseInfo")
    public CommonResponse selectBaseInfo(@RequestParam String id) {


        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.getBaseInfoById(id));
    }

    @ApiOperation("获取项目进展情况")
    @GetMapping("selectStageInfo")
    public CommonResponse selectStageInfo(@RequestParam String id) {

        StageInfoVO StageInfoById = reportService.getStageInfoById(id);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, StageInfoById);
    }

    @ApiOperation("获取施工机械信息")
    @GetMapping("selectMachineInfo")
    public CommonResponse selectMachineInfo(@RequestParam String id, String type) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, machineService.machineList(id, type));
    }

    @ApiOperation("获取施工人员信息")
    @GetMapping("selectPersonInfo")
    public CommonResponse selectPersonInfo(@RequestParam String id, String type) {
        personnelService.PersonnelList(id, type);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, personnelService.PersonnelList(id, type));
    }


    @ApiOperation("获取形象进度图片")
    @GetMapping("selectImageStage")
    public CommonResponse selectImageStage(String bizId) {
        List<SysFile> sysFiles = sysFileService.list(new QueryWrapper<SysFile>()
                .eq("biz_id", bizId)
                .eq("biz_type", SysFileTypeEnums.IMAGE_PROGRESS.getCode())
                .eq("status", StatusEnums.STATUS_VALID.getCode())
        );
        return new CommonResponse(ResponseTypeEnums.SUCCESS, sysFiles);
    }


    @ApiOperation("获取下周计划及安排")
    @GetMapping("selectNextPlan")
    public CommonResponse selectNextPlan(@RequestParam String id) {

        StageInfoVO nextPlan = reportService.getNextPlan(id);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, nextPlan);
    }

    @ApiOperation("获取现场整改纪要")
    @GetMapping("selectSiteRectify")
    public CommonResponse selectSiteRectify(@RequestParam String id) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.getSiteRectify(id));
    }

    @ApiOperation("获取安全生产情况")
    @GetMapping("selectSafeInfo")
    public CommonResponse selectSafeInfo(@RequestParam String id) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.getSafeInfo(id));
    }

    @ApiOperation("获取本周收发资料汇总")
    @GetMapping("selectInOutFile")
    public CommonResponse selectInOutFile(@RequestParam String id) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.getInOutFile(id));
    }

    @ApiOperation("获取可能影响工程的因素")
    @GetMapping("selectEffect")
    public CommonResponse selectEffect(@RequestParam String id) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.getEffect(id));
    }

    @ApiOperation("获取现场其他需要说明")
    @GetMapping("selectSiteOtherInfo")
    public CommonResponse selectSiteOtherInfo(@RequestParam String id) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.getSiteOtherInfo(id));
    }

    @ApiOperation(value = "获取必填字段反馈", notes = "id为周报ID,当返回对象属性为 true 是标识为当前选项卡字段有空值约束未填写,反之则填写")
    @GetMapping("selectMustInfo")
    public CommonResponse selectMustInfo(@RequestParam String id) throws IllegalAccessException {
        MustInfoVO mustInfoVO = new MustInfoVO();
        WeeklyMustFiledVO filedVO = reportService.selectMustInfo(id);
        boolean b = CheckObjectIsNullUtils.objCheckIsNull(filedVO);
        if (!b) {
            mustInfoVO.setBaseInfo(true);
            return new CommonResponse(ResponseTypeEnums.SUCCESS, mustInfoVO);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS, mustInfoVO);
    }

}

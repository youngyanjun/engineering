package com.bimforest.ems.modules.department.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.MessageEnums;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.common.utils.WeeklyUtil;
import com.bimforest.ems.modules.department.vo.AddReportVO;
import com.bimforest.ems.modules.department.vo.GetAssistReportWeeklyVO;
import com.bimforest.ems.modules.department.entity.ReportList;
import com.bimforest.ems.modules.department.entity.Report;
import com.bimforest.ems.modules.department.service.ReportListService;
import com.bimforest.ems.modules.department.service.ReportService;
import com.bimforest.ems.modules.base.service.BaseOrganizationService;
import com.bimforest.ems.modules.sys.service.EngineeringSystemWeeklyService;
import com.bimforest.ems.pojo.DateWeekly;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-12
 */
@Api(description = "周月报列表")
@RestController
@RequestMapping("/api/web/department/report-report-list/")
public class ReportListController {

    @Autowired
    ReportListService reportReportListService;

    @Autowired
    EngineeringSystemWeeklyService weeklyService;

    @Autowired
    ReportService reportService;

    @Autowired
    BaseOrganizationService organizationService;

    /**
     * 部门报表列表
     */

    @GetMapping("getReportList")
    @ApiOperation("部门报表列表")
    public CommonResponse getReportList(String organizationId,
                                        Integer reportType,
                                        String reportName,
                                        @RequestParam(required = false, defaultValue = "10")
                                        @ApiParam("每页显示条数") int pageSize,
                                        @RequestParam(required = false, defaultValue = "1")
                                        @ApiParam("当前页码") int pageNum) {
        Page page = new Page(pageNum, pageSize);

        return new CommonResponse(ResponseTypeEnums.SUCCESS,
                reportReportListService.getReportList(page, organizationId, reportType, reportName));

    }

    @ApiOperation("周报周次获取")
    @GetMapping("AddDepartmentReportWeekly")
    public CommonResponse AddDepartmentReportWeekly(String organizationId) {

        String startDate = weeklyService.selectList().get(0).getStartDate();

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date newDate = new Date();
        String endDate = format.format(newDate);
        List<DateWeekly> weekly = WeeklyUtil.getWeekly(startDate, endDate);
        //查出本项目所有已经存在的周次list >周报日期在设置的日期范围之内的
        List<DateWeekly> weeklyReport = reportReportListService.selectListWeeklyById(organizationId);
        //循环遍历出所有的已经存在的周次的周报,去除已经存在的周报
        weekly.removeAll(weeklyReport);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, weekly);
    }

    @ApiOperation("月报月次获取")
    @GetMapping("AddDepartmentReportMonth")
    public CommonResponse AddDepartmentReportMonth(String organizationId) {
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        //查出本项目所有已经存在的月次list >月报报日期在设置的日期范围之内的
        List<Integer> monthReport = reportReportListService.selectListMonthByOrganizationType(organizationId, year);
        //循环出12 个月的集合
        List<Integer> month = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            month.add(i);
        }
        month.removeAll(monthReport);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, month);
    }


    @OperationLog(module = "公司部门", action = "新建周/月报")
    @ApiOperation("新建周报")
    @PostMapping("getDepartmentReportWeekly")
    public CommonResponse AddDepartmentReport(@RequestBody AddReportVO addReport) throws ParseException {
        //本周周月报ID
        String reportId = null;
        //获取上周月报ID
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        //上一个周月报ID
        String LastReportId = reportReportListService.getId(addReport.getReportType(), addReport.getWeeklyMonth());
        if (LastReportId == null) {
            LastReportId = "0";
        }
        List<GetAssistReportWeeklyVO> departmentReportWeekly = reportService.getDepartmentReportWeekly(addReport.getOrganizationType(), LastReportId);
        if (departmentReportWeekly.size() < 1) {
            return new CommonResponse(ResponseTypeEnums.FAILED, MessageEnums.FIAL_SYS_2019.getCode(), MessageEnums.FIAL_SYS_2019.getMessage());
        }
        //获取部门名称
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotEmpty(addReport.getOrganizationId()), "id", addReport.getOrganizationId());
        String name = organizationService.getOne(queryWrapper).getName();
        //创建周月报列表
        ReportList reportList = new ReportList();
        reportList.setWeeklyMonth(addReport.getWeeklyMonth());


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");

        reportList.setConstructionDateStart(format.parse(addReport.getConstructionDateStart()));//施工开始日期
        reportList.setConstructionDateFinish(format.parse(addReport.getConstructionDateFinis()));//施工结束日期
        reportList.setReportType(addReport.getReportType());
        if (addReport.getReportType() == 0) {
            reportList.setReportName(name + " 第" + addReport.getWeeklyMonth() + "周 周报");
        } else {
            reportList.setReportName(name + addReport.getWeeklyMonth() + "月 月报");
        }

        reportList.setOrgId(addReport.getOrganizationId());
        reportReportListService.insert(reportList);
        reportList.setStatus(StatusEnums.TEMPORARY_STORAGE.getCode());
        reportReportListService.updateById(reportList);
        reportId = reportList.getId();
        //获取周月报项目
        //创建周月报
        for (int i = 0; i < departmentReportWeekly.size(); i++) {
            Report reportWeekly = new Report();
            reportWeekly.setReportId(reportId);
            reportWeekly.setEngineeringId(departmentReportWeekly.get(i).getEngineeringId());
            reportWeekly.setEngName(departmentReportWeekly.get(i).getEngName());
            reportWeekly.setStage(departmentReportWeekly.get(i).getStage());
            reportWeekly.setPlan(departmentReportWeekly.get(i).getPlan());
            reportWeekly.setProjectLeader(departmentReportWeekly.get(i).getProjectLeader());
            reportWeekly.setCoordination(departmentReportWeekly.get(i).getCoordination());
            reportWeekly.setDepartment(name);
            reportService.createInsert(reportWeekly);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportId);
    }


}

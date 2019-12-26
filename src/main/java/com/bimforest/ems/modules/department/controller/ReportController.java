package com.bimforest.ems.modules.department.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.modules.department.vo.GetOrgEngUserListVO;
import com.bimforest.ems.modules.department.vo.GetOrgEngUserVO;
import com.bimforest.ems.modules.department.vo.SubmitDepartmentMonthReportVO;
import com.bimforest.ems.modules.department.entity.Report;
import com.bimforest.ems.modules.department.entity.ReportList;
import com.bimforest.ems.modules.department.service.ReportListService;
import com.bimforest.ems.modules.department.service.ReportService;
import com.bimforest.ems.modules.sys.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 公司部门周月报表—周报 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-11
 */
@Slf4j
@Api(description = "部门周报管理")
@RestController
@RequestMapping("/api/web/department/report-weekly/")
public class ReportController {


    @Autowired
    ReportService reportService;

    @Autowired
    ReportListService reportListService;


    @ApiOperation("编辑部门周月报")
    @PostMapping("AddDepartmentReportWeekly")
    public CommonResponse editDepartmentReport(@RequestBody List<Report> reportList) throws ParseException {

        //删除原有周月报
        if (reportList.size() > 0) {
            //根据记录ID删除已有信息
            reportService.deleteBatchByReportId(reportList);
        }
        //保存修改的数据
        reportService.saveBatch(reportList);
        //更新列表更新时间
        //获取当前时间
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式

        if (reportList.size() > 0) {
            reportList.get(0).getReportId();
            ReportList report = new ReportList();
            report.setUpdateDate((Timestamp) df.parse(df.format(new Date())));
            report.setId(reportList.get(0).getReportId());
            reportListService.updateById(report);
        }
        //重新插入新的周月报
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation(value = "获取周月报详情", notes = "\"edit\": \"edit\" 标识为当前用户可以编辑  为null 则标识为用户不可编辑")
    @GetMapping("EditDepartmentReport")
    public CommonResponse getDepartmentReport(String reportId) {
        String userId = ((SysUser) SecurityUtils.getSubject().getPrincipal()).getId();
        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.getDepartmentReport(reportId, userId));
    }

    @ApiOperation("提交部门周报")
    @PostMapping("SubmitDepartmentReport")
    public CommonResponse SubmitDepartmentReportWeekly(@RequestBody SubmitDepartmentMonthReportVO reportVO) {
        Integer status = StatusEnums.SUBMIT.getCode();
        //提交周报
        int i = reportListService.submitReport(reportVO.getReportId(), status);
        //发送信息
        if (i > 0) {
            reportListService.sm(reportVO);

        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation("@下拉列表")
    @GetMapping("getOrgEngUser")
    public CommonResponse getOrgEngUser(String organizationType) {
        List<GetOrgEngUserListVO> getOrgEngUserListVOS = new ArrayList<>();
        //获取所有已知项目
        List<String> engId = reportListService.getEngId(organizationType);
        //获取每个项目的其他组织机构的管理员并拼接
        for (int i = 0; i < engId.size(); i++) {
            //查询到的每个项目的信息
            List<GetOrgEngUserVO> orgEngUser = reportListService.getOrgEngUser(organizationType, engId.get(i));
            GetOrgEngUserListVO getOrgEngUserList = new GetOrgEngUserListVO();
            getOrgEngUserList.setEngId(engId.get(i));
            getOrgEngUserList.setList(orgEngUser);
            getOrgEngUserListVOS.add(i,getOrgEngUserList);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS, getOrgEngUserListVOS);
    }


}

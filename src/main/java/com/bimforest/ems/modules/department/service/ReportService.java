package com.bimforest.ems.modules.department.service;

import com.bimforest.ems.modules.department.vo.GetAssistReportWeeklyVO;
import com.bimforest.ems.modules.department.vo.ReportVO;
import com.bimforest.ems.modules.department.entity.Report;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 公司部门周月报表—周报 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-11
 */
public interface ReportService extends IService<Report> {

    List<GetAssistReportWeeklyVO> getDepartmentReportWeekly(String organizationType, String reportId);

    int createInsert(Report reportWeekly);


    List<ReportVO> getDepartmentReport(String reportId, String userId);


    void deleteBatchByReportId(List<Report> reportList);

}

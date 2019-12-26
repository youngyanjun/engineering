package com.bimforest.ems.modules.department.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.department.vo.GetAssistReportWeeklyVO;
import com.bimforest.ems.modules.department.vo.ReportVO;
import com.bimforest.ems.modules.department.entity.Report;
import com.bimforest.ems.modules.department.mapper.ReportListMapper;
import com.bimforest.ems.modules.department.mapper.ReportMapper;
import com.bimforest.ems.modules.department.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 公司部门周月报表—周报 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-11
 */
@Service
public class ReportServiceImpl extends ServiceImpl<ReportMapper, Report> implements ReportService {

    @Autowired
    ReportMapper reportMapper;
    @Autowired
    ReportListMapper reportListMapper;

    @Override
    public List<GetAssistReportWeeklyVO> getDepartmentReportWeekly(String organizationType, String reportId) {
        return reportMapper.getDepartmentReportWeekly(organizationType, reportId);
    }

    /**
     * 新建插入数据
     *
     * @param reportWeekly
     * @return
     */
    @Override
    public int createInsert(Report reportWeekly) {
        return reportMapper.insert(reportWeekly);
    }

    /**
     * 删除周报
     *
     * @param reportList
     * @return
     */
    @Override
    public void deleteBatchByReportId(List<Report> reportList) {
        for (int i = 0; i < reportList.size(); i++) {
            //删除周报
             reportMapper.deleteReport(reportList.get(i).getId());
        }
    }

    @Override
    public List<ReportVO> getDepartmentReport(String reportId, String userId) {

        return reportMapper.selectReportList(reportId, userId);
    }


}

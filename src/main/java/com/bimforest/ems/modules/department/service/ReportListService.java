package com.bimforest.ems.modules.department.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.department.vo.GetOrgEngUserVO;
import com.bimforest.ems.modules.department.vo.ReportListVO;
import com.bimforest.ems.modules.department.vo.SubmitDepartmentMonthReportVO;
import com.bimforest.ems.modules.department.entity.ReportList;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.management.VO.SelectRawDataListVO;
import com.bimforest.ems.pojo.DateWeekly;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-12
 */
public interface ReportListService extends IService<ReportList> {

    IPage<ReportListVO> getReportList(Page page, String organizationId, Integer reportType, String reportName);

    List<DateWeekly> selectListWeeklyById(String organizationType);

    String getId(Integer reportType, Integer weeklyMonth);

    List<Integer> selectListMonthByOrganizationType(String organizationId, String year);

    int submitReport(String reportId,Integer status);


    int sm(SubmitDepartmentMonthReportVO reportVO);

    int insert(ReportList reportList);

    List<GetOrgEngUserVO> getOrgEngUser(String organizationId,String engineeringId);

    List<String> getEngId(String organizationType);

    IPage<SelectRawDataListVO> getRawDataList(SelectRawDataListVO rawDataList);

}

package com.bimforest.ems.modules.department.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.department.vo.GetOrgEngUserVO;
import com.bimforest.ems.modules.department.vo.ReportListVO;
import com.bimforest.ems.modules.department.entity.ReportList;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.management.VO.SelectRawDataListVO;
import com.bimforest.ems.pojo.DateWeekly;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-12
 */
public interface ReportListMapper extends BaseMapper<ReportList> {

    IPage<ReportListVO> getReportList(Page page,
                                      @Param("organizationId") String organizationId,
                                      @Param("reportType") Integer reportType,
                                      @Param("reportName") String reportName);

    List<DateWeekly> selectListWeeklyById(@Param("organizationId") String organizationId);

    String getId(@Param("reportType") Integer reportType, @Param("weeklyMonth") Integer weeklyMonth);

    List<Integer> selectListMonthByOrganizationType(@Param("organizationId") String organizationId,
                                                    @Param("year") String year);

    int updateStatusByReport(@Param("reportId") String reportId,@Param("status") Integer status);

    List<GetOrgEngUserVO> getOrgEngUser(@Param("organizationType") String organizationType,@Param("engineeringId") String engineeringId);


    List<String> getEngId(@Param("organizationType") String organizationType);

    IPage<SelectRawDataListVO> getRawDataList(Page page, @Param("rawDataList") SelectRawDataListVO rawDataList);

}

package com.bimforest.ems.modules.department.mapper;

import com.bimforest.ems.modules.department.vo.GetAssistReportWeeklyVO;
import com.bimforest.ems.modules.department.vo.ReportVO;
import com.bimforest.ems.modules.department.entity.Report;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 公司部门周月报表—周报 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-11
 */
public interface ReportMapper extends BaseMapper<Report> {

    List<GetAssistReportWeeklyVO> getDepartmentReportWeekly(Report reportWeekly);

    List<GetAssistReportWeeklyVO> getDepartmentReportWeekly(@Param("organizationType") String organizationType,
                                                            @Param("reportId") String reportId);

    int deleteByReportId(@Param("reportId") String reportId);

    List<ReportVO> selectReportList(@Param("reportId") String reportId, @Param("userId") String userId);

    int deleteReport(@Param("id") String id);

}

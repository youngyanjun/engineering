package com.bimforest.ems.modules.construction.mapper;

import com.bimforest.ems.modules.construction.vo.ReportForecastSelectVO;
import com.bimforest.ems.modules.construction.vo.ReportForecastVO;
import com.bimforest.ems.modules.construction.entity.ReportForecast;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
public interface ReportForecastMapper extends BaseMapper<ReportForecast> {

    List<ReportForecastVO> queryReportForecast(ReportForecastSelectVO reportForecastVO);

    int insertBatch(List<ReportForecast> sysWeatherForecasts);

    int updateStatusByStatus(@Param("fromStatus") Integer fromStatus, @Param("toStatus") Integer toStatus, @Param("reportId") String reportId);


}

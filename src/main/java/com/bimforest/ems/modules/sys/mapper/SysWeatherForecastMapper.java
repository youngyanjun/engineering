package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.sys.entity.SysWeatherForecast;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-06
 */
public interface SysWeatherForecastMapper extends BaseMapper<SysWeatherForecast> {

    int insertBatch(List<SysWeatherForecast> sysWeatherForecasts);

    List<SysWeatherForecast> queryReportForecast(@Param("latitude") String latitude, @Param("longitude") String longitude, @Param("predictDates") List<Date> dates);

}

package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.SysWeatherForecast;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  天气预报服务类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-06
 */
public interface SysWeatherForecastService extends IService<SysWeatherForecast> {

    int insertBatch(List<SysWeatherForecast> sysWeatherForecasts);
}

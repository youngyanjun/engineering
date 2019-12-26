package com.bimforest.ems.modules.sys.service.impl;

import com.bimforest.ems.modules.sys.entity.SysWeatherForecast;
import com.bimforest.ems.modules.sys.mapper.SysWeatherForecastMapper;
import com.bimforest.ems.modules.sys.service.SysWeatherForecastService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-06
 */
@Service
public class SysWeatherForecastServiceImpl extends ServiceImpl<SysWeatherForecastMapper, SysWeatherForecast> implements SysWeatherForecastService {

    @Autowired
    SysWeatherForecastMapper sysWeatherForecastMapper;

    @Override
    public int insertBatch(List<SysWeatherForecast> sysWeatherForecasts) {
        return sysWeatherForecastMapper.insertBatch(sysWeatherForecasts);
    }
}

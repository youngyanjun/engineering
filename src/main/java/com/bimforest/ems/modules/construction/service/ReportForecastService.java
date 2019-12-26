package com.bimforest.ems.modules.construction.service;

import com.bimforest.ems.modules.construction.vo.ReportForecastAddVO;
import com.bimforest.ems.modules.construction.vo.ReportForecastSelectVO;
import com.bimforest.ems.modules.construction.vo.ReportForecastVO;
import com.bimforest.ems.modules.construction.entity.ReportForecast;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
public interface ReportForecastService extends IService<ReportForecast> {

    List<ReportForecastVO> queryReportForecast(ReportForecastSelectVO reportForecastVO);

    void addForecast(ReportForecastAddVO reportForecastAddVO);
}

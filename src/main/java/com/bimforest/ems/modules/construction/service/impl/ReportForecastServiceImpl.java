package com.bimforest.ems.modules.construction.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bimforest.ems.common.utils.Md5Utils;
import com.bimforest.ems.modules.construction.vo.ReportForecastAddVO;
import com.bimforest.ems.modules.construction.vo.ReportForecastSelectVO;
import com.bimforest.ems.modules.construction.vo.ReportForecastVO;
import com.bimforest.ems.modules.construction.entity.ReportForecast;
import com.bimforest.ems.modules.construction.entity.WengineeringBaseInfo;
import com.bimforest.ems.modules.construction.mapper.ReportForecastMapper;
import com.bimforest.ems.modules.construction.service.ReportForecastService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.construction.service.WengineeringBaseInfoService;
import com.bimforest.ems.modules.sys.entity.SysWeatherForecast;
import com.bimforest.ems.modules.sys.mapper.SysWeatherForecastMapper;
import com.bimforest.utils.DateUtils;
import com.bimforest.utils.StrUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
@Service
public class ReportForecastServiceImpl extends ServiceImpl<ReportForecastMapper, ReportForecast> implements ReportForecastService {

    @Autowired
    private ReportForecastMapper reportForecastMapper;
    @Autowired
    private SysWeatherForecastMapper weatherForecastMapper;
    @Autowired
    private WengineeringBaseInfoService wengineeringBaseInfoService;

    /**
    * @Description: 查看天气预报
    * @param reportForecastVO
    * @author max.zhang
    * @date 2019/12/10 11:45
    */
    @Override
    public List<ReportForecastVO> queryReportForecast(ReportForecastSelectVO reportForecastVO) {
        WengineeringBaseInfo wengineeringBaseInfo = wengineeringBaseInfoService.selectOne(reportForecastVO.getProjectId());
        reportForecastVO.setLatitude(wengineeringBaseInfo.getLatitude());
        reportForecastVO.setLongitude(wengineeringBaseInfo.getLongitude());
        List<ReportForecastVO> reportForecasts = reportForecastMapper.queryReportForecast(reportForecastVO);
        reportForecasts.stream().forEach(x -> {
            x.setWeek(dateToWeek(x.getPredictDate()));
        });
        return reportForecasts;
    }

    /**
    * @Description: 日期转换为星期几
    * @param date
    * @return java.lang.String
    * @author max.zhang
    * @date 2019/12/10 11:44
    */
    private String dateToWeek(Date date) {
        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        // 获得一个日历
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        // 指示一个星期中的某天。
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
    * @Description: 保存天气预报
    * @param reportForecastAddVO
    * @return void
    * @author max.zhang
    * @date 2019/12/10 11:45
    */
    @Override
    public void addForecast(ReportForecastAddVO reportForecastAddVO) {

        if (CollectionUtil.isNotEmpty(reportForecastAddVO.getReportForecastVOList())) {
            // 首先使周报的天气预报失效
            reportForecastMapper.updateStatusByStatus(1,-1,reportForecastAddVO.getReportId());
            List<Date> dates = reportForecastAddVO.getReportForecastVOList().stream().map(x ->
                    x.getPredictDate()
            ).collect(Collectors.toList());
            // 获取项目经纬度
            WengineeringBaseInfo wengineeringBaseInfo = wengineeringBaseInfoService.selectOne(reportForecastAddVO.getProjectId());
            // 获取系统官方天气预报，保存到map中，主要用来后期与周报的天气预报做变更比对
            List<SysWeatherForecast> sysWeatherForecasts = weatherForecastMapper.queryReportForecast(wengineeringBaseInfo.getLatitude(), wengineeringBaseInfo.getLongitude(), dates);
            Map<String, String> map = new HashMap<>();
            for (SysWeatherForecast sysWeatherForecast : sysWeatherForecasts) {
                map.put(DateUtils.dateToString(sysWeatherForecast.getPredictDate()), Md5Utils.getMd5(sysWeatherForecast.getConditionDay()
                        + sysWeatherForecast.getTempDay()
                        + sysWeatherForecast.getWindDirDay()));
            }
            List<ReportForecast> reportForecasts = new LinkedList<>();
            ReportForecast reportForecast = null;
            // 与周报的天气预报做变更比对
            for(ReportForecastVO reportForecastVO:reportForecastAddVO.getReportForecastVOList()){
                // 系统天气预报hash
                String sys = map.get(DateUtils.dateToString(reportForecastVO.getPredictDate()));
                // 周报天气预报hash
                String report = Md5Utils.getMd5(reportForecastVO.getConditionDay()
                        + reportForecastVO.getTempDay()
                        + reportForecastVO.getWindDirDay());
                // 如果系统天气预报与周报天气预报不一致，表示用户修改了周报的天气预报，则把变更保存到周报天气预报表中
                if(!StrUtils.equals(sys,report)){
                    reportForecast = new ReportForecast();
                    reportForecast.setId(UUID.randomUUID().toString());
                    reportForecast.setReportId(reportForecastAddVO.getReportId());
                    BeanUtils.copyProperties(reportForecastVO,reportForecast);
                    reportForecast.setLatitude(wengineeringBaseInfo.getLatitude());
                    reportForecast.setLongitude(wengineeringBaseInfo.getLongitude());
                    reportForecasts.add(reportForecast);
                }
            }
            reportForecastMapper.insertBatch(reportForecasts);
        }


    }

}

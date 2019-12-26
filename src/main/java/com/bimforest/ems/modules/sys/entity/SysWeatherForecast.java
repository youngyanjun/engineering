package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 天气预报实体类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-06
 */
@Data
@Accessors(chain = true)
@TableName("sys_weather_forecast")
public class SysWeatherForecast {

    /**
     * 日期
     */
    private Date predictDate;

    /**
     * 天气情况：多云或晴或其他
     */
    private String conditionDay;

    /**
     * 白天温度
     */
    private String tempDay;

    /**
     * 风向
     */
    private String windDirDay;

    /**
     * 风速
     */
    private String windSpeedDay;

    /**
     * 天气预报自己的更新时间
     */
    private Date updatetime;

    private int cityId;

    // 经度
    private String longitude;

    // 纬度
    private String latitude;


}

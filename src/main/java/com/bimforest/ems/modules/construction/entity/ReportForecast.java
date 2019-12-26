package com.bimforest.ems.modules.construction.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("report_forecast")
public class ReportForecast extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 周报id
     */
    private String reportId;

    /**
     * 天气预报日期
     */
    private Date predictDate;

    /**
     * 天气
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
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;




}

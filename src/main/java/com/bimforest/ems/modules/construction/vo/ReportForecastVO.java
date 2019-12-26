package com.bimforest.ems.modules.construction.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author max.zhang
 * @title:
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/911:44
 * @Copyright (C) 量树科技
 */
@Data
public class ReportForecastVO {

    private static final long serialVersionUID = 1L;


    /**
     * 天气预报日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date predictDate;

    /**
     * 星期
     */
    private String week;

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




}

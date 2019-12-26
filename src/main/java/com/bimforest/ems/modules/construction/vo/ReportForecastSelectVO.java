package com.bimforest.ems.modules.construction.vo;

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
public class ReportForecastSelectVO {

    /**
     * 周报id
     */
    private String reportId;

    private String projectId;
    /**
     * 天气开始日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date startDate;

    /**
     * 天气结束日期
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    /**
     * 经度
     */
    private String longitude;

    /**
     * 纬度
     */
    private String latitude;

}

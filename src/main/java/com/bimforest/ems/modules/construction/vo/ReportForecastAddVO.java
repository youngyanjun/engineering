package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

import java.util.List;

/**
 * @author max.zhang
 * @title:
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/911:44
 * @Copyright (C) 量树科技
 */
@Data
public class ReportForecastAddVO {


    private String projectId;

    private String reportId;

    private List<ReportForecastVO> reportForecastVOList;

}

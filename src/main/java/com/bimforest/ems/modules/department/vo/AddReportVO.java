package com.bimforest.ems.modules.department.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-12-12 16:08
 */
@Data
public class AddReportVO {
    /**
     * 组织ID
     */
    private String organizationId;

    /**
     * 组织类型
     */
    private String organizationType;
    /**
     * 报表名称
     */
    private String reportName;
    /**
     * 报表类型
     */
    private Integer reportType;
    /**
     * 月份
     */
    private Integer weeklyMonth;

    /**
     * 施工开始日期
     */
    private String constructionDateStart;
    /**
     * 施工结束日期
     */
    private String constructionDateFinis;

}

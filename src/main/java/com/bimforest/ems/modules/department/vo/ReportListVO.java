package com.bimforest.ems.modules.department.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author youngyanjun
 * @date 2019-12-12 14:59
 */
@Data
public class ReportListVO {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 更新时间
     */
    private Date updateDate;

    /**
     * 状态
     */
    private String status;

    /**
     * 报表名称
     */
    private String reportName;

    /**
     * 报表类型
     */
    private String reportType;

    /**
     * 月份
     */
    private Integer month;

    /**
     * 周次
     */
    private Integer weekly;
}

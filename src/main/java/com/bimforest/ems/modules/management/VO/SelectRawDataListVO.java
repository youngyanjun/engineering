package com.bimforest.ems.modules.management.VO;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author youngyanjun
 * @date 2019-12-23 10:58
 */
@Data
public class SelectRawDataListVO {
    /**
     * 报表ID
     */
    private String id;
    /**
     * 名称
     */
    private String reportName;
    /**
     * 报表类型
     */
    private Integer reportType;
    private String orgType;
    /**
     * 来源
     */
    private String orgId;

    /**
     * 来源
     */
    private String orgName;
    /**
     * 月份
     */
    private String reportMonth;
    /**
     * 当前页大小
     */
    private Integer pageSize;
    /**
     * 当前页码
     */
    private Integer pageNum;
    /**
     * 最近修改时间
     */
    private Timestamp updateDate;
}

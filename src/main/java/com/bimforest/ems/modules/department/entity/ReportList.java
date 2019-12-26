package com.bimforest.ems.modules.department.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-12
 */
@Data
@Accessors(chain = true)
@TableName("report_department")
public class ReportList extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
    private Integer month;

    /**
     * 施工开始日期
     */
    private Date constructionDateStart;

    /**
     * 施工结束日期
     */
    private Date constructionDateFinish;

    /**
     * 周次
     */
    private Integer weeklyMonth;

    /**
     * 组织类型
     */
    private String organizationType;
    /**
     * 组织ID
     */
    private String  orgId;



}

package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author youngyanjun
 * @date 2019-11-28 11:14
 */
@Data
public class WeeklyBaseInfoVO {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 组织ID
     */
    private String organizationId;
    /**
     * 暂存、提交
     */
    private Integer state;
    private String reportName;


    /**
     * 工程名称
     */
    private String constructionName;

    /**
     * 建设单位
     */
    private String constructionUnit;

    /**
     * 质监单位
     */
    private String qualityInspectionUnit;

    /**
     * 质监站
     */
    private String qualityInspectionStation;

    /**
     * 项目经理
     */
    private String projecLeader;

    /**
     * 技术负责人
     */
    private String technicalLeader;

    /**
     * 安全员
     */
    private String safeMan;

    /**
     * 合同总价
     */
    private BigDecimal contractPrice;

    /**
     * 已经回款、已收款
     */
    private BigDecimal moneyBack;
    /**
     * 周次
     */
    private Integer weeklyNum;

    /**
     * 施工开始日期
     */
    private String constructionDateStart;

    /**
     * 施工结束日期
     */
    private String constructionDateFinis;
    /**
     * 完工率
     */
    private Integer completionRate;

    /**
     * 报送者
     */
    private String reportBy;

}

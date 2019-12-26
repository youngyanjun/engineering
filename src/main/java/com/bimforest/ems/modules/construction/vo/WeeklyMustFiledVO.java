package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author youngyanjun
 * @date 2019-12-09 9:09
 */
@Data
public class WeeklyMustFiledVO {
    private String id;
    /**
     * 工程名称
     */
    private String constructionName;

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
     * 完工率
     */
    private Integer completionRate;


}

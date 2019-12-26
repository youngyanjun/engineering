package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author youngyanjun
 * @date 2019-12-09 11:27
 */
@Data
public class ImageProgressVO {
    private String id;
    /**
     * 项目经理
     */
    private String projectLeader;

    /**
     * 建筑面积
     */
    private String floorArea;

    /**
     * 工程名称
     */
    private String engName;

    /**
     * 合同价
     */
    private String contractPrice;

    /**
     * 实际开工日期
     */
    private Date startDate;

    /**
     * 计划竣工日期
     */
    private Date endDate;

    /**
     * 完工比率
     */
    private Integer
            completionRate;

    /**
     * 本月产值
     */
    private String

            monthOutputValue;

    /**
     * 编制人
     */
    private String editBy;

    /**
     * 审核人
     */
    private String auditorBy;

    /**
     * 工程合同形象进度
     */
    private String currentContractProgress;

    /**
     * 本期执行形象进度
     */
    private String currentCurrentProgress;

    /**
     * 下月施工进度计划
     */
    private String nextProgressPlan;

    /**
     * 项目ID
     */
    private String engineeringId;

    /**
     * 安全及质量问题
     */
    private String securityQualityProblem;

    /**
     * 现场安装人员
     */
    private String siteInstallationPersonne;

    /**
     * 报表名称
     */
    private String reportName;
    /**
     * 月次
     */
    private Integer monthNum;
}

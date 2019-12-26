package com.bimforest.ems.modules.construction.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import com.bimforest.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("mengineering_image_progress")
public class MengineeringImageProgress extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 每月产值
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
     * 备注
     */
    private String remark;

    /**
     * 项目id
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
     * 报表类型  0 项目部 周报  1 项目部 月报 3 项目部形象进度
     */
    private String reportType;
    /**
     * 月次
     */
    private Integer month_num;


    /**
     * 报表名称
     */
    private String reportName;
}

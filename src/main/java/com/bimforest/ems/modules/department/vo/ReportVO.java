package com.bimforest.ems.modules.department.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-12-19 11:24
 */
@Data
public class ReportVO {
    private String id;
    /**
     * 周报ID
     */
    private String reportId;
    /**
     * 项目ID--0
     */
    private String engineeringId;
    /**
     * 项目名称---1
     */
    private String engName;
    /**
     * 部门---2
     */
    private String department;
    /**
     * BIM应用阶段 --3
     */
    private String stage;
    /**
     * 本周计划情况 --4
     */
    private String plan;
    /**
     * 本周计划完成情况--5
     */
    private String planComplete;
    /**
     * 本月/周计划滞后原因  ---6
     */
    private String backWard;
    /**
     * 解决方案 --7
     */
    private String solution;
    /**
     * 下周工作计划  --8
     */
    private String nextPlan;
    /**
     * 完成工作情况 --9
     */
    private String workText;
    /**
     * 总进度未完成滞后原因  10
     */
    private String total;
    /**
     * 解决方案 --11
     */
    private String solutionAll;
    /**
     * 需要其他部门配合的事宜 --12
     */
    private String coordination;
    /**
     * 项目负责人--13
     */
    private String projectLeader;
    /**
     * 是否可以编辑
     */
    private String edit;

}

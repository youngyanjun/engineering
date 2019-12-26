package com.bimforest.ems.modules.department.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 公司部门周月报表—周报
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-11
 */
@Data
@Accessors(chain = true)
@TableName("report_department_list")
public class Report extends BaseEntity {

    private static final long serialVersionUID = 1L;

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




}

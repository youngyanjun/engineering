package com.bimforest.ems.modules.construction.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 工程报表周报---施工人员
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-03
 */
@Data
@Accessors(chain = true)
@TableName("reportw_engineering_personnel")
public class WengineeringReportPersonnel {

    private static final long serialVersionUID = 1L;
    private  String id;
    /**
     * 管理人员
     */
    private String manager;

    /**
     * 土建
     */
    private String civilengineering;

    /**
     * 装配工种
     */
    private String prefabricated;

    /**
     * 水电安装
     */
    private String waterPowerInstall;

    /**
     * 暖通安装
     */
    private String havcInstall;

    /**
     * 装饰装修
     */
    private String correct;

    /**
     * 室外工程
     */
    private String outdoor;

    /**
     * 其他
     */
    private String other;

    /**
     * 关联周次ID
     */
    private String reportId;

    /**
     * 类型
     */
    private String weeklyType;

    /**
     * 日期
     */
    private Date dateAt;

    /**
     * 当前星期
     */
    private String week;

    /**
     * 汽车泵
     */
    private String autoPumps;


}

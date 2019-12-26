package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author youngyanjun
 * @date 2019-12-03 13:13
 */
@Data
public class WengineeringReportPersonnelVO {


    private String id;
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
    private String weeklyId;

    /**
     * 类型
     */
    private String weeklyType;

    /**
     * 日期
     */
    private LocalDate dateAt;

    /**
     * 当前星期
     */
    private String week;

    /**
     * 汽车泵
     */
    private String autoPumps;


}

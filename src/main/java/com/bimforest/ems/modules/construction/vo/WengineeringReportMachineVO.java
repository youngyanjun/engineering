package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

import java.time.LocalDate;

/**
 * @author youngyanjun
 * @date 2019-12-03 13:19
 */
@Data
public class WengineeringReportMachineVO {

    /**
     * 汽车泵
     */
    private String autoPumps;

    /**
     * 电焊机
     */
    private String electricWeldingMachine;

    /**
     * 砼运输车
     */
    private String concreteTransport;

    /**
     * 叉车
     */
    private String forklift;

    /**
     * 料运输车
     */
    private String materialTransport;

    /**
     * 汽车起重机
     */
    private String craneTruck;

    /**
     * 其他设备
     */
    private String other;

    /**
     * 关联周次ID
     */
    private String weeklyId;

    /**
     * 发生时间
     */
    private LocalDate dateAt;

    /**
     * 星期
     */
    private String weekNum;

    /**
     * 类型
     */
    private String weeklyType;

}

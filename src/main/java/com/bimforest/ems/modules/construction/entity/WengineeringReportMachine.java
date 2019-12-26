package com.bimforest.ems.modules.construction.entity;

import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 工程报表周报---施工机械
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-03
 */
@Data
@Accessors(chain = true)
@TableName("reportw_engineering_machine")
public class WengineeringReportMachine {

    private static final long serialVersionUID = 1L;
    private  String id;
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
     * 切割机
     */
    private String cutter;

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
    private String reportId;

    /**
     * 发生时间
     */
    private Date dateAt;


    /**
     * 类型
     */
    private String weeklyType;


}

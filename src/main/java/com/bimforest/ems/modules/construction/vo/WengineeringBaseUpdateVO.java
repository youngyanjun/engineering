package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: WengineeringBaseUpdateVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1414:48
 * @Copyright (C) 量树科技
 */
@Data
public class WengineeringBaseUpdateVO {

    /**
     * 工程名称
     */
    private String engineeringName;

    /**
     * 项目简称
     */
    private String abbreviation;

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
     * 项目所在经度
     */
    private  String longitude;
    /**
     * 项目所在维度
     */
    private String latitude;

    private String orgId;

    private String address;
}

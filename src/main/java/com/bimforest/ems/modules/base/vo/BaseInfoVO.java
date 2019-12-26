package com.bimforest.ems.modules.base.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-12-24 8:56
 */
@Data
public class BaseInfoVO {

    private String id;
    /**
     * 工程名称
     */
    private String engineeringName;

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
     * 项目简称
     */
    private String abbreviation;
    /**
     * 项目所在经度
     */
    private  String longitude;
    /**
     * 项目所在维度
     */
    private String latitude;

    /**
     * 组织机构Id
     */
    private String orgId;
    /**
     * 地址
     */
    private String address;
}

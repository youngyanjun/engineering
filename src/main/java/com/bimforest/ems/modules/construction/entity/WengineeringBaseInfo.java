package com.bimforest.ems.modules.construction.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 工程报表—周报
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-06
 */
@Data
@Accessors(chain = true)
@TableName("wengineering_base_info")
public class WengineeringBaseInfo  {

    private static final long serialVersionUID = 1L;

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


    private String orgId;

    private String address;

}

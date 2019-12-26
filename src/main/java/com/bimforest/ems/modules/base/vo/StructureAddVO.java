package com.bimforest.ems.modules.base.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: StructureAddVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1311:54
 * @Copyright (C) 量树科技
 */
@Data
public class StructureAddVO {

    private String id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 组织架构名称
     */
    private String structureName;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 组织id
     */
    private String orgId;

}

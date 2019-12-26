package com.bimforest.ems.modules.base.vo;

import com.bimforest.ems.pojo.BaseTreeNode;
import lombok.Data;

/**
 * @author max.zhang
 * @title: StructureSelectVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1311:24
 * @Copyright (C) 量树科技
 */
@Data
public class StructureTreeVO extends BaseTreeNode {

    /**
     * 备注
     */
    private String remark;

    /**
     * 组织架构名称
     */
    private String structureName;

    /**
     * 组织id
     */
    private String orgId;

}

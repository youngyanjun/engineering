package com.bimforest.ems.modules.base.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: EngineeringSingleUpdateVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/2319:26
 * @Copyright (C) 量树科技
 */
@Data
public class EngineeringSingleUpdateVO {

    private String id;
    /**
     * 工程id
     */
    private String engineeringId;

    /**
     * 单体名称
     */
    private String singleName;

    /**
     * 是否必填
     */
    private Boolean mandatory;

}

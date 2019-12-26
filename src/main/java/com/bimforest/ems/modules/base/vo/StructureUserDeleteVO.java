package com.bimforest.ems.modules.base.vo;

import lombok.Data;

import java.util.List;

/**
 * @author max.zhang
 * @title: StructureUserDeleteVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1314:15
 * @Copyright (C) 量树科技
 */
@Data
public class StructureUserDeleteVO {

    private String structureId;

    private List<String> userIds;
}

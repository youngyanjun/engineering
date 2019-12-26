package com.bimforest.ems.modules.base.vo;

import lombok.Data;

import java.util.List;

/**
 * @author max.zhang
 * @title: UserStructureAddVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1316:51
 * @Copyright (C) 量树科技
 */
@Data
public class UserStructureAddVO {

    private List<String> userIds;

    private String structureId;
}

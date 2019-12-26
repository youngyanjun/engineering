package com.bimforest.ems.modules.base.vo;

import lombok.Data;

import java.util.List;

/**
 * @author max.zhang
 * @title: OrgUserDeleteVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1411:35
 * @Copyright (C) 量树科技
 */
@Data
public class OrgUserDeleteVO {

    private String orgId;

    private List<String> userIds;
}

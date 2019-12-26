package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

import java.util.List;

/**
 * @author max.zhang
 * @title: RoleUserDeleteVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1414:15
 * @Copyright (C) 量树科技
 */
@Data
public class RoleUserDeleteVO {

    private String roleId;

    private List<String> userIds;
}

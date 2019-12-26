package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

import java.util.List;

/**
 * @author max.zhang
 * @title: RoleUserAddVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1317:09
 * @Copyright (C) 量树科技
 */
@Data
public class RoleUserAddVO {

    private List<String> userIds;
    private String roleId;
}

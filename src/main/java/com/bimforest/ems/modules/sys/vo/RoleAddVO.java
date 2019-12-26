package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: RoleAddVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/149:47
 * @Copyright (C) 量树科技
 */
@Data
public class RoleAddVO {

    private String roleName;

    private String remark;

    private String orgId;

    private String parentId;

}

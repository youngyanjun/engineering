package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: RoleUpdateVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/149:51
 * @Copyright (C) 量树科技
 */
@Data
public class RoleUpdateVO {

    private String id;

    private String roleName;

    private String remark;

    private String parentId;

}

package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: RoleUserSelectVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1316:01
 * @Copyright (C) 量树科技
 */
@Data
public class RoleUserSelectVO {

    private String userId;

    private String userName;

    private String phone;

    private String roles;

    private String eMail;
}

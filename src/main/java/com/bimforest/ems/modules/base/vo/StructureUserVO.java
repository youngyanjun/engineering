package com.bimforest.ems.modules.base.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: StructureUserVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1313:48
 * @Copyright (C) 量树科技
 */
@Data
public class StructureUserVO {

    private String userId;

    private String userName;

    private String phone;

    private String eMail;

    private String roles;
}

package com.bimforest.ems.modules.base.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author max.zhang
 * @title: EngineeringRanageSelectVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/2117:46
 * @Copyright (C) 量树科技
 */
@Data
public class EngineeringRanageSelectVO {

    private String id;

    private String userId;

    private String userName;

    private String engineeringId;

    private String engineeringName;

    private String engineeringStage;

    private Date createDate;
}

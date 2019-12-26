package com.bimforest.ems.modules.base.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: EngineeringRanageUpdateVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/2117:55
 * @Copyright (C) 量树科技
 */
@Data
public class EngineeringRanageUpdateVO {


    private String id;
    /**
     * 项目所处阶段（‘1’ 未开始  2 ‘进行中’ 3  ‘已完成’）
     */
    private String engineeringStage;

    /**
     * 负责人，对应sys_user表的id
     */
    private String personInCharge;


}

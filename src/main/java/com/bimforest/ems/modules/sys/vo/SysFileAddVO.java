package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

import java.util.List;

/**
 * @author max.zhang
 * @title: SysFileAddVO
 * @projectName bimforest-ems
 * @description: 新增文件
 * @date 2019/12/417:12
 * @Copyright (C) 量树科技
 */
@Data
public class SysFileAddVO {

    private List<SysFileVO> sysFileVOS;

    private String bizId;
}

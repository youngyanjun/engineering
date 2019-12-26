package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: SysFileVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/416:58
 * @Copyright (C) 量树科技
 */
@Data
public class SysFileVO {

    /**
     * 业务id
     */
    private String bizId;

    /**
     * 真实的文件名
     */
    private String realName;

    private String fileName;

    /**
     * 备注
     */
    private String remark;

}

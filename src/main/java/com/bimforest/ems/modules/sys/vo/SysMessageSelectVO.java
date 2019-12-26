package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: SysMessageSelectVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1710:58
 * @Copyright (C) 量树科技
 */
@Data
public class SysMessageSelectVO {

    private String id;

    // 消息内容
    private String content;

    // 接收时间
    private String createDate;

    // 消息状态
    private boolean readFlag;

    // 来源
    private String source;

    // 填写人
    private String userName;
}

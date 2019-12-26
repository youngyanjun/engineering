package com.bimforest.ems.common.enums;

/**
 * @author max.zhang
 * @title: SysMessageTypeEnums
 * @projectName bimforest-ems
 * @description: 系统消息类型枚举
 * @date 2019/12/1710:20
 * @Copyright (C) 量树科技
 */
public enum SysMessageTypeEnums {

    MONTH_REPORT("MONTH_REPORT", "月报"),
   ;


    private String code;


    private String message;

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    SysMessageTypeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

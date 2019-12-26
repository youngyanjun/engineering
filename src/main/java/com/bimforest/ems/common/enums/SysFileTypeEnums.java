package com.bimforest.ems.common.enums;

/**
* @Description: 文件类型枚举
* @author max.zhang
* @date 2019/12/4 15:41
*/
public enum SysFileTypeEnums {

    IMAGE_PROGRESS("IMAGE_PROGRESS", "形象进度"),
    PROBLEM_FEEDBACK("PROBLEM_FEEDBACK", "问题反馈"),
    SMS_LOGIN("SMS_LOGIN", "登录");


    private String code;


    private String message;

    public String getCode() {
        return code;
    }


    public String getMessage() {
        return message;
    }

    SysFileTypeEnums(String code, String message) {
        this.code = code;
        this.message = message;
    }
}

package com.bimforest.ems.common.enums;

/**
 * @author youngyanjun
 * @date 2019-11-01 16:49
 */
public enum  DefaultEnums {

    DEFAULT_PASSWORD("123","默认用户密码"),
    ENGINEERING_STAGE_READY("-1","未开始"),
    ENGINEERING_STAGE_PROGRESS("1","进行中"),
    ENGINEERING_STAGE_ENG("-2","已结束"),
    RANGE_CONSTRUCTION("8","项目范围施工"),
    ORGANIZATION_TYPE("1","项目部");








    private String code;

    private String message;

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    DefaultEnums(String acode, String amessage) {
        this.code = acode;
        this.message = amessage;
    }

}

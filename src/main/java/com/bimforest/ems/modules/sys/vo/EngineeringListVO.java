package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-11-12 17:17
 */
@Data
public class EngineeringListVO {
    private  String id;
    private  String name;
    private String gather;
    private String managerName;
    private String managerPhone;
    private String createDate;
    private String managerId;

    /**
     * 项目编码
     */
    private String engineeringCode;

    /**
     * 项目简称
     */
    private String engineeringAbbreviation;



    /**
     * 合同金额
     */
    private String contentPrice;
}

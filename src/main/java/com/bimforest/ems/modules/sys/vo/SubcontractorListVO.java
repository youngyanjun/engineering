package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-11-04 15:21
 */
@Data
public class SubcontractorListVO {

    private String id;


    private String unitName;




    private  String engineeringSum;


    /**
     * 联系人
     */
    private String contact;

    /**
     * 电话
     */
    private String phone;

    /**
     * 创建时间
     */
    private String createDate;
}

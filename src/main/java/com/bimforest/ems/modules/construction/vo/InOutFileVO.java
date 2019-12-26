package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-11-29 14:44
 */
@Data
public class InOutFileVO {
    /**
     * 周报ID
     */
    private String id;

    /**
     * 公司内部联络
     */
    private String receiveFileIn;

    /**
     * 公司外部联络
     */
    private String receiveFileOut;
}

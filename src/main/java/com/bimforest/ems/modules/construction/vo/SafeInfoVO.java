package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @title: ${}
 * @date 2019-11-29 14:40
 */
@Data
public class SafeInfoVO {
    /**
     * 周报ID
     */
    private String id;

    /**
     * 本周安全生产情况
     */
    private String safeInfo;

    /**
     * 本周重大危险源
     */
    private String dangerSource;

    /**
     * 下周重大危险源
     */
    private String nextDangerSource;

    /**
     * 本周现场检查情况
     */
    private String siteCheck;
}

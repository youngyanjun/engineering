package com.bimforest.ems.modules.log.VO;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-11-12 8:46
 */
@Data
public class LogVO {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 操作用户
     */
    private String userName;

    /**
     * 操作模块
     */
    private String module;

    /**
     * 用户行为--操作行为（function）
     */
    private String action;
    /**
     * 创建时间
     */
    private String createDate;
}

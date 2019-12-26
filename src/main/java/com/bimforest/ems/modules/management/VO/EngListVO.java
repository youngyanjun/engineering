package com.bimforest.ems.modules.management.VO;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-12-24 11:18
 */
@Data
public class EngListVO {
    /**
     * 项目简称
     */
    private String engName;
    /**
     * 是否查看
     */
    private Integer status;
    /**
     * 对应组织ID
     */
    private String orgId;
    /**
     *
     */
}

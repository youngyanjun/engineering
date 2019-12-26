package com.bimforest.ems.modules.management.VO;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-12-24 11:51
 */
@Data
public class OrgListVO {

    /**
     * 公司名称
     */
    private String orgName;
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

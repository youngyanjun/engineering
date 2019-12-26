package com.bimforest.ems.modules.sys.vo;

import com.bimforest.ems.pojo.BaseTreeNode;
import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-11-19 11:53
 */
@Data
public class OrganizationTemplateListVO extends BaseTreeNode {
    /**
     * 组织名称
     */
    private String name;

    /**
     * 说明，备注
     */
    private String remark;
    /**
     * 用户组织ID主键
     */
    private String orgId;
}

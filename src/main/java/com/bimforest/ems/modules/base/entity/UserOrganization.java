package com.bimforest.ems.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-10
 */
@Data
@Accessors(chain = true)
@TableName("user_organization")
public class UserOrganization {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 组织ID
     */
    private String organizationId;

    /**
     * 用户ID
     */
    private String userId;

    /**
     * 子节点ID
     */
    private String childId;


}

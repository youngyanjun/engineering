package com.bimforest.ems.modules.construction.entity;

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
@TableName("engineering_organization_user")
public class EngineeringOrganizationUser  {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 备注
     */
    private String remark;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 类型
     */
    private String type;

    /**
     * 管理员ID
     */
    private String managerId;

    /**
     * 关联项目ID
     */
    private String engineeringId;

    /**
     * 父ID
     */
    private String parentId;

    /**
     * ID
     */
    private String orgId;


}

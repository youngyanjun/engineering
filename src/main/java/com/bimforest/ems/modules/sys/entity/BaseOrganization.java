package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_organization")
public class BaseOrganization extends BaseEntity {

    private static final long serialVersionUID = 1L;

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
     * 备注
     */
    private String remark;
}

package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import com.bimforest.ems.pojo.BaseTreeNode;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.lang.ref.PhantomReference;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
@Data
@Accessors(chain = true)
@TableName("engineering_organization_template")
public class EngineeringOrganizationTemplate  {

    private static final long serialVersionUID = 1L;
    /**
     * 主键ID
     */
    private String id;
    /**
     * 组织名称
     */
    private String name;

    /**
     * 说明，备注
     */
    private String remark;
    /**
     * 父类ID
     */
    private String parentId;
}

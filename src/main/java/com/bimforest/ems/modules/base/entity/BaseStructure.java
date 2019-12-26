package com.bimforest.ems.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.bimforest.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-13
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_structure")
public class BaseStructure extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String remark;

    /**
     * 组织架构名称
     */
    private String structureName;

    /**
     * 父id
     */
    private String parentId;

    /**
     * 组织id
     */
    private String orgId;


}

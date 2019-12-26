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
 * @since 2019-12-23
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_engineering_single")
public class BaseEngineeringSingle extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 工程id
     */
    private String engineeringId;

    /**
     * 单体名称
     */
    private String singleName;

    /**
     * 是否必填
     */
    private Boolean mandatory;

    /**
     * 单体顺序
     */
    private Integer singleOrder;



}

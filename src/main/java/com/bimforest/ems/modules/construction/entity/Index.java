package com.bimforest.ems.modules.construction.entity;

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
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("report_index")
public class Index extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 报表ID
     */
    private String reportId;

    /**
     * 报表所有者
     */
    private String reportBy;

    /**
     * 报表关联项目ID
     */
    private String engineeringId;


}

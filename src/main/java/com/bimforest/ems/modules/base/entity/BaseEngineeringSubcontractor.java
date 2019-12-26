package com.bimforest.ems.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目关联分包商信息
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
@Data
@Accessors(chain = true)
@TableName("base_engineering_subcontractor")
public class BaseEngineeringSubcontractor {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * 分包单位ID
     */
    private String subcontractorId;

    /**
     * 分包合同金额
     */
    private String subcontractorPrice;

    /**
     * 关联项目ID
     */
    private String engineeringId;



}

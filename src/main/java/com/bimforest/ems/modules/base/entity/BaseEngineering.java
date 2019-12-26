package com.bimforest.ems.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 项目管理表
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-12
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_engineering")
public class BaseEngineering extends BaseEntity {

    private static final long serialVersionUID = 1L;



    /**
     * 项目名称
     */
    private String engineeringName;

    /**
     * 项目编码
     */
    private String engineeringCode;

    /**
     * 项目简称
     */
    private String engineeringAbbreviation;

    /**
     * 管理员
     */
    private String managerId;

    /**
     * 合同金额
     */
    private String contractPrice;
    /**
     * 组织ID
     */
    private String orgId;






}

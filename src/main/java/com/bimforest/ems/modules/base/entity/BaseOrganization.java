package com.bimforest.ems.modules.base.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.time.LocalDateTime;
import com.bimforest.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 组织
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-16
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_organization")
public class BaseOrganization extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String remark;

    /**
     * 组织名称
     */
    private String name;

    /**
     * 类型：dept公司部门，engineering项目部，leader领导层
     */
    private String category;

    /**
     * 管理员ID
     */
    private String managerId;

    /**
     * 项目所在经度
     */
    private String longitude;

    /**
     * 项目所在维度
     */
    private String latitude;

    /**
     * 地址
     */
    private String address;

    /**
     * 阶段名称（‘1’ 设计 ‘2’ bim ‘3’ 深化-钢构 ‘4’ 深化-PC ‘5’ 制造钢构 ‘6’ 制造PC ‘7’ 制造门窗 ‘8’ 施工）
     */
    private String type;


}

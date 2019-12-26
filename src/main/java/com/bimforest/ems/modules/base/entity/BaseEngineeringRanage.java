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
 * @since 2019-12-21
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("base_engineering_ranage")
public class BaseEngineeringRanage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 阶段名称（‘1’ 设计 ‘2’ bim ‘3’ 深化-钢构 ‘4’ 深化-PC ‘5’ 制造钢构 ‘6’ 制造PC ‘7’ 制造门窗 ‘8’ 施工）
     */
    private String ranageType;

    /**
     * 关联项目id
     */
    private String engineeringId;

    /**
     * 项目所处阶段（‘1’ 未开始  2 ‘进行中’ 3  ‘已完成’） 
     */
    private String engineeringStage;

    /**
     * 负责人，对应sys_user表的id
     */
    private String personInCharge;



}

package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.Period;
import java.util.Set;

/**
 * <p>
 * 权限菜单表
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_menu")
public class SysMenu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String remark;


    /**
     * 菜单名称
     */
    private String menuName;

    /**
     * 菜单级别
     */
    private String level;

    /**
     * 权限标识
     */
    private String perms;
    /**
     * 父ID
     */
    private String parentId;

    /**
     * 权限标识
     */
    private String href;

    /**
     * 组织类型
     */
    private String org_type;
}

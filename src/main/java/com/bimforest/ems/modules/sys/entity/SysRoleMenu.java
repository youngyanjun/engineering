package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 角色菜单表
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
@Data
@Accessors(chain = true)
@TableName("sys_role_menu")
public class SysRoleMenu {

    private static final long serialVersionUID = 1L;
    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 菜单id，增删改查
     */
    private String menuId;



}

package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.util.Set;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_role")
public class SysRole extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 备注
     */
    private String remark;

    /**
     * 创建时间

     */

    /**
     * 角色名称
     */
    private String roleName;



    /**
     * 角色级别
     */
    private String level;
    /**
     * 父ID
     */
    private String parentId;

    /**
     * 用户角色
     */
    private String role;
    /**
     * 角色所属项目ID
     */
    private String orgId;
}

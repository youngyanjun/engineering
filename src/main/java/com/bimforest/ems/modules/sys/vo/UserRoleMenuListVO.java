package com.bimforest.ems.modules.sys.vo;

import com.bimforest.ems.modules.sys.entity.SysMenu;
import com.bimforest.ems.modules.sys.entity.SysRole;
import lombok.Data;

import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-11-26 10:15
 */
@Data
public class UserRoleMenuListVO {
    /**
     * 角色
     */
    private List<SysRole> listRole;
    /**
     * 权限
     */
    private List<SysMenu> listMenu;
}

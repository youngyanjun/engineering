package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.SysRoleMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.AddRoleMenuVO;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
public interface SysRoleMenuService extends IService<SysRoleMenu> {

    int deleteByRoleId(String roleId);

    int insertRoleMenuBatch(List<AddRoleMenuVO> list);
}

package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.SysMenu;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.MenuListVO;

import java.util.List;

/**
 * <p>
 * 权限菜单表 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
public interface SysMenuService extends IService<SysMenu> {

    List<MenuListVO> selectMenuList(String id);

    List<SysMenu> selectRoleMenu(String id);

}

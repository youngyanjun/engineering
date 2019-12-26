package com.bimforest.ems.modules.sys.service.impl;

import com.bimforest.ems.common.utils.TreeNodeUtil;
import com.bimforest.ems.modules.sys.entity.SysMenu;
import com.bimforest.ems.modules.sys.mapper.SysMenuMapper;
import com.bimforest.ems.modules.sys.service.SysMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.MenuListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 权限菜单表 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
@Service
public class SysMenuServiceImpl extends ServiceImpl<SysMenuMapper, SysMenu> implements SysMenuService {

    @Autowired
    SysMenuMapper menuMapper;


    @Override
    public List<MenuListVO> selectMenuList(String id) {

        List<MenuListVO> menuListVO = menuMapper.selectMenuList(id);
        List<MenuListVO> menuListTreeVO = TreeNodeUtil.assembleTree(menuListVO);
        return menuListTreeVO;
    }

    @Override
    public List<SysMenu> selectRoleMenu(String id) {
        return menuMapper.selectRoleMenu(id);
    }
}

package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.sys.entity.SysMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.MenuListVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 权限菜单表 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
public interface SysMenuMapper extends BaseMapper<SysMenu> {

    List<MenuListVO> selectMenuList(@Param("id") String id);

    List<SysMenu> selectRoleMenu(@Param("id") String id);
}

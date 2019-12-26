package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.sys.entity.SysRoleMenu;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.AddRoleMenuVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 角色菜单表 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
public interface SysRoleMenuMapper extends BaseMapper<SysRoleMenu> {

    int insertRoleMenuBatch(@Param("list") List<AddRoleMenuVO> list);
}

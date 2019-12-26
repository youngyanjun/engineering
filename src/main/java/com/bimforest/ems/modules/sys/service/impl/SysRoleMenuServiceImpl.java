package com.bimforest.ems.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bimforest.ems.modules.sys.entity.SysRoleMenu;
import com.bimforest.ems.modules.sys.mapper.SysRoleMenuMapper;
import com.bimforest.ems.modules.sys.service.SysRoleMenuService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.AddRoleMenuVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 角色菜单表 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
@Service
public class SysRoleMenuServiceImpl extends ServiceImpl<SysRoleMenuMapper, SysRoleMenu> implements SysRoleMenuService {
    @Autowired
    SysRoleMenuMapper roleMenuMapper;
    private String roleId;

    /**
     * 根据role_id 删除权限信息
     * @param roleId
     * @return
     */
    @Override
    public int deleteByRoleId(String roleId) {
        this.roleId = roleId;

        int delete = roleMenuMapper.delete(new QueryWrapper<SysRoleMenu>().eq("role_id", roleId));
        return delete;
    }

    /**
     * 重新建立权限
     * @param list
     * @return
     */
    @Override
    public int insertRoleMenuBatch(List<AddRoleMenuVO> list) {
        /*int insertRoleMenuBatch = roleMenuMapper.;*/


        return 1;
    }
}

package com.bimforest.ems.modules.sys.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.bimforest.ems.common.utils.TreeNodeUtil;
import com.bimforest.ems.modules.base.vo.StructureAddVO;
import com.bimforest.ems.modules.sys.entity.SysRole;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.entity.SysUserRole;
import com.bimforest.ems.modules.sys.mapper.SysRoleMapper;
import com.bimforest.ems.modules.sys.mapper.SysUserRoleMapper;
import com.bimforest.ems.modules.sys.service.SysRoleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.*;
import com.bimforest.utils.StrUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    SysRoleMapper roleMapper;

    @Autowired
    SysUserRoleMapper sysUserRoleMapper;


    /**
     * 角色列表
     *
     * @return
     */
    @Override
    public List<RoleListVO> RoleTreeList() {
        List<RoleListVO> list = roleMapper.selectToList();
        List<RoleListVO> listTree = TreeNodeUtil.assembleTree(list);
        return listTree;
    }

    /**
     * @param orgId
     * @Description: 根据组织id获取角色
     * @author max.zhang
     * @date 2019/12/12 16:35
     */
    @Override
    public List<RoleListVO> selectRoles(String orgId) {
        StructureAddVO structureAddVO = new StructureAddVO();
        structureAddVO.setOrgId(orgId);
        List<RoleListVO> list = roleMapper.selectRoles(structureAddVO);
        List<RoleListVO> listTree = TreeNodeUtil.assembleTree(list);
        return listTree;
    }

    /**
     * @param name
     * @param remark
     * @param parentId
     * @return int
     * @Description: 新增角色
     * @author max.zhang
     * @date 2019/12/13 8:58
     */
    @Override
    public int addRole(String name, String remark, String parentId, String orgId) {
        SysRole role = new SysRole();
        role.setParentId(parentId);
        role.setRemark(remark);
        role.setRoleName(name);
        role.setOrgId(orgId);
        int record = roleMapper.insert(role);
        return record;
    }


    /**
     * 修改角色
     *
     * @param name
     * @param remark
     * @param id
     * @return
     */
    @Override
    public int updateRoleTemplateList(String name, String remark, String id) {
        SysRole role = new SysRole();
        role.setId(id);
        role.setRemark(remark);
        role.setRoleName(name);
        return roleMapper.updateById(role);
    }

    /**
     * @return int
     * @Description: 角色修改
     * @author max.zhang
     * @date 2019/12/13 10:38
     */
    @Override
    public int updateRole(RoleUpdateVO roleUpdateVO) {
        SysRole role = new SysRole();
        BeanUtils.copyProperties(roleUpdateVO, role);
        return roleMapper.updateById(role);
    }

    /**
     * 删除角色
     *
     * @param id
     * @return
     */
    @Override
    public int deleteRoleTemplateList(String id) {
        SysRole role = new SysRole();
        role.setId(id);
        return roleMapper.deleteById(role);
    }

    /**
     * @param id 角色id
     * @Description: 角色删除
     * @author max.zhang
     * @date 2019/12/13 10:38
     */
    @Override
    public Integer deleteRole(String id) {
        SysRole role = new SysRole();
        role.setId(id);
        return roleMapper.deleteRole(id);
    }

    @Override
    public List<SysRole> selectUserRoles(String userId) {
        return roleMapper.getUserRoles(userId);
    }

    @Override
    public int addUsersForRole(RoleUserAddVO roleUserAddVO) {
        if (CollectionUtil.isNotEmpty(roleUserAddVO.getUserIds()) && StrUtils.isNotBlank(roleUserAddVO.getRoleId())) {
            List<String> userIds = roleUserAddVO.getUserIds();
            List<SysUserRole> sysUserRoles = new LinkedList<>();
            String roleId = roleUserAddVO.getRoleId();
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            SysUserRole sysUserRole = null;
            for (String userId : userIds) {
                sysUserRole = new SysUserRole();
                sysUserRole.setRoleId(roleId);
                sysUserRole.setUserId(userId);
                sysUserRole.setCreateBy(user.getId());
                sysUserRole.setId(UUID.randomUUID().toString());
                sysUserRoles.add(sysUserRole);
            }
            return sysUserRoleMapper.insertBatch(sysUserRoles);
        }
        return 0;

    }

    @Override
    public List<RoleUserSelectVO> selectUsersForRole(String orgId, String roleId) {
        return roleMapper.selectUsersForRole(orgId, roleId);
    }

    @Override
    public int deleteUsersForRole(RoleUserDeleteVO roleUserDeleteVO) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return sysUserRoleMapper.delete(roleUserDeleteVO.getUserIds(), user.getId(), roleUserDeleteVO.getRoleId());
    }

    @Override
    public List<SysRole> selectUserRolesByOrgId(String userId, String orgId) {
        return baseMapper.selectUserRolesByOrgId(userId, orgId);
    }

    @Override
    public List<SysUser> selectUsersNotInRole(String roleId, String userName) {
        return baseMapper.selectUsersNotInRole(roleId, userName);
    }
}

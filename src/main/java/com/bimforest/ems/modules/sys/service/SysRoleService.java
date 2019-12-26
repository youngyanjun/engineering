package com.bimforest.ems.modules.sys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.entity.SysRole;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.vo.*;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
public interface SysRoleService extends IService<SysRole> {

    //角色树形节点
    List<RoleListVO> RoleTreeList();

    int addRole(String name, String remark, String parentId, String orgId);

    int updateRoleTemplateList(String name, String remark, String id);

    int deleteRoleTemplateList(String id);

    List<SysRole> selectUserRoles(String userId);


    List<RoleListVO> selectRoles(String orgId);

    Integer deleteRole(String id);

    int updateRole(RoleUpdateVO roleUpdateVO);

    int addUsersForRole(RoleUserAddVO roleUserAddVO);

    List<RoleUserSelectVO> selectUsersForRole(String orgId, String roleId);

    int deleteUsersForRole(RoleUserDeleteVO roleUserDeleteVO);

    List<SysRole> selectUserRolesByOrgId(String userId, String orgId);

    List<SysUser> selectUsersNotInRole(String roleId,String roleName);

}

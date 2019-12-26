package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.base.vo.StructureAddVO;
import com.bimforest.ems.modules.sys.entity.SysRole;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.vo.RoleListVO;
import com.bimforest.ems.modules.sys.vo.RoleUserSelectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
* @Description: 角色mapper
* @return
* @author max.zhang
* @date 2019/12/13 10:30
*/
public interface SysRoleMapper extends BaseMapper<SysRole> {

    List<RoleListVO> selectToList();

    List<SysRole> getUserRoles(@Param("userId") String userId);

    List<RoleListVO> selectRoles(@Param("structureAddVO") StructureAddVO structureAddVO);

    Integer deleteRole(@Param("roleId") String roleId);

    List<RoleUserSelectVO> selectUsersForRole(@Param("orgId") String orgId, @Param("roleId") String roleId);

    List<SysRole> selectUserRolesByOrgId(@Param("userId") String userId, @Param("orgId") String orgId);

    List<SysUser> selectUsersNotInRole(@Param("roleId") String roleId,@Param("userName") String userName);
}

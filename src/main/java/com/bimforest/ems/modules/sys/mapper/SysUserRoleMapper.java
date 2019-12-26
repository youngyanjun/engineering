package com.bimforest.ems.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.entity.SysRole;
import com.bimforest.ems.modules.sys.entity.SysUserRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 用户角色表 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-08
 */
public interface SysUserRoleMapper extends BaseMapper<SysUserRole> {

    int insertBatch(List<SysUserRole> sysUserRoles);

    int delete(@Param("userIds") List<String> userIds, @Param("updateUser") String updateUser, @Param("roleId") String roleId);

}

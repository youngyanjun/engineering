package com.bimforest.ems.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.SysUserVO;
import com.bimforest.ems.modules.sys.vo.UserListEngineering;
import com.bimforest.ems.modules.base.vo.QueryUserListVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-10-30
 */

public interface SysUserMapper extends BaseMapper<SysUser> {
    //修改表某个表中“status”中的状态值
    int updateStatus(@Param("list") List<String> list, @Param("status") Integer status);

    //重置密码为原始密码
    int updatePassword(@Param("list") List list, @Param("password") String password);

    //查询列表
    IPage<SysUserVO> selectUserList(Page page, @Param("sysUserName") String sysUserName);

    int checkEmailIsEnable(@Param("email") String email, @Param("id") String id);

    List<UserListEngineering> queryUserList();

    int selectOneEmail(@Param("email") String email, @Param("id") String id);

    List<QueryUserListVO> selectListUser(@Param("userName") String userName);
}

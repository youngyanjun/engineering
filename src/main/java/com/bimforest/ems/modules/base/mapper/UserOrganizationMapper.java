package com.bimforest.ems.modules.base.mapper;

import com.bimforest.ems.modules.base.entity.UserOrganization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-10
 */
public interface UserOrganizationMapper extends BaseMapper<UserOrganization> {

    //更具用户ID批量删除
    int deleteBatch(@Param("userId") List<String> userId);

    int deleteBatchNotRootNote(@Param("userId") List<String> userId);

    List<String> selectUserList(String organizationId);
}

package com.bimforest.ems.modules.base.mapper;

import com.bimforest.ems.modules.base.entity.BaseStructureUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-13
 */
public interface BaseStructureUserMapper extends BaseMapper<BaseStructureUser> {

    int delete(@Param("userIds") List<String> userIds, @Param("updateUser") String updateUser, @Param("structureId") String structureId);

    int deleteByOrgId(@Param("userIds") List<String> userIds, @Param("updateUser") String updateUser, @Param("orgId") String orgId);

    int insertBatch(List<BaseStructureUser> baseStructureUsers);
}

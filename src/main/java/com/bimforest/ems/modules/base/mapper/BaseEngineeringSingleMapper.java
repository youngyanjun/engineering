package com.bimforest.ems.modules.base.mapper;

import com.bimforest.ems.modules.base.entity.BaseEngineeringSingle;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-23
 */
public interface BaseEngineeringSingleMapper extends BaseMapper<BaseEngineeringSingle> {

    int delete(@Param("ids") List<String> ids, @Param("updateUser") String updateUser);
}

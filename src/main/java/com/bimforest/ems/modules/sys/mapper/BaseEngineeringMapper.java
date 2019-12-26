package com.bimforest.ems.modules.sys.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.base.entity.BaseEngineering;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.EngineeringListVO;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 * 项目管理表 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-12
 */
public interface BaseEngineeringMapper extends BaseMapper<BaseEngineering> {

    IPage<EngineeringListVO> selectEngineeringList(Page page, @Param("engineeringName") String engineeringName);

    int selectEngineeringCode(@Param("engineeringCode") String engineeringCode);

    int selectOtherEngineeringCode(@Param("id") String id, @Param("engineeringCode") String engineeringCode);

    void updateOrgIdById(@Param("id") String id);

}


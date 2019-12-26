package com.bimforest.ems.modules.base.mapper;

import com.bimforest.ems.modules.base.vo.EngineeringRanageSelectVO;
import com.bimforest.ems.modules.base.entity.BaseEngineeringRanage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-21
 */
public interface BaseEngineeringRanageMapper extends BaseMapper<BaseEngineeringRanage> {

    List<EngineeringRanageSelectVO> selectEngineeringRanages();

    int deleteList(@Param("engineeringCode") String engineeringCode);

    List<BaseEngineeringRanage> selectRanageList(@Param("id") String id);
}

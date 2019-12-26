package com.bimforest.ems.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.base.entity.BaseEngineeringSubcontractor;
import com.bimforest.ems.modules.sys.vo.EngineeringInvolveSubcontractVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
public interface BaseEngineeringSubcontractorMapper extends BaseMapper<BaseEngineeringSubcontractor> {

    int deletes(@Param("engineeringCode") String engineeringCode);

    List<EngineeringInvolveSubcontractVO> selectSubcontractList(@Param("engineeringId") String engineeringId);
}

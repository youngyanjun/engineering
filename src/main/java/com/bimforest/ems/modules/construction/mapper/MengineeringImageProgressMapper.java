package com.bimforest.ems.modules.construction.mapper;

import com.bimforest.ems.modules.construction.entity.MengineeringImageProgress;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
public interface MengineeringImageProgressMapper extends BaseMapper<MengineeringImageProgress> {

    List<Integer> getCurrentMonth(@Param("year") String year,@Param("organizationId") String organizationId);

}

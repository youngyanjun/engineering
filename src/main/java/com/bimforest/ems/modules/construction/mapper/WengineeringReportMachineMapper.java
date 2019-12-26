package com.bimforest.ems.modules.construction.mapper;

import com.bimforest.ems.modules.construction.entity.WengineeringReportMachine;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 * 工程报表周报---施工机械 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-03
 */
public interface WengineeringReportMachineMapper extends BaseMapper<WengineeringReportMachine> {

    int deleteByWeeklyId(@Param("id") String id);

    int deleteByNextWeeklyId(String id);

    List<WengineeringReportMachine> getMachineList(@Param("id") String id,@Param("type") String type);





}

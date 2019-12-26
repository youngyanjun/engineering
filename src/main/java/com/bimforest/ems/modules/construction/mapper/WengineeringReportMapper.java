package com.bimforest.ems.modules.construction.mapper;

import com.bimforest.ems.modules.construction.vo.*;
import com.bimforest.ems.modules.construction.entity.WengineeringReport;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.pojo.DateWeekly;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-28
 */
public interface WengineeringReportMapper extends BaseMapper<WengineeringReport> {

    List<DateWeekly> selectYearItemList(@Param("nowDate") Integer nowDate);


    WeeklyBaseInfoVO getBaseInfoById(@Param("id") String id );

    StageInfoVO getStageInfoById(@Param("id") String id);

    StageInfoVO getNextPlanById(@Param("id")String id);

    SiteRectifyVO getSiteRectify(@Param("id")String id);

    SafeInfoVO getSafeInfo(@Param("id")String id);

    InOutFileVO getInOutFile(@Param("id")String id);

    EffectVO getEffect(@Param("id")String id);

    SiteOtherInfoVO getSiteOtherInfo(@Param("id")String id);

    StageInfoVO getNextStageInfo(@Param("weekly") Integer weekly,@Param("engineeringId") String engineeringId);

    String getWeeklyIdByWeekly(@Param("weekly") Integer weekly,@Param("engineeringId") String engineeringId);

    WeeklyMustFiledVO reportMapper(@Param("id") String id);

}

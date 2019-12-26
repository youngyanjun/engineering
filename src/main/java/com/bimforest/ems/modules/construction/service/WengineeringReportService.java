package com.bimforest.ems.modules.construction.service;

import com.bimforest.ems.modules.construction.vo.*;
import com.bimforest.ems.modules.construction.entity.WengineeringReport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.pojo.DateWeekly;

import java.text.ParseException;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-28
 */
public interface WengineeringReportService extends IService<WengineeringReport> {

    String insertBaseInfo(WeeklyBaseInfoVO baseInfoVO) throws ParseException;

    int insertStageInfo(StageInfoVO stageInfoVO);

    int insertSiteRectify(SiteRectifyVO siteRectifyVO);

    int insertSafeInfo(SafeInfoVO safeInfoVO);

    List<DateWeekly> selectListWeeklyById(String id);

    int insertNextPlan(StageInfoVO stageInfoVO);

    int insertInOutFile(InOutFileVO baseInfoVO);

    int insertSiteOtherInfo(SiteOtherInfoVO otherInfoVO);

    int insertEffect(EffectVO effectVO);

    WeeklyBaseInfoVO getBaseInfoById(String id);

    StageInfoVO getStageInfoById(String id);

    StageInfoVO getNextPlan(String id);

    SiteRectifyVO getSiteRectify(String id);

    SafeInfoVO getSafeInfo(String id);

    InOutFileVO getInOutFile(String id);

    EffectVO getEffect(String id);

    SiteOtherInfoVO getSiteOtherInfo(String id);


    int submitReport(SubmitReport submitReport);

    String getWeeklyIdByWeekly(Integer newWeekly,String engineeringId);

    StageInfoVO getNextStageInfo(Integer weekly,String engineeringId);

    WeeklyMustFiledVO selectMustInfo(String id);

}

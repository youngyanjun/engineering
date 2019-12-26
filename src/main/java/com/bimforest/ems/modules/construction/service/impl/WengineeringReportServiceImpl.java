package com.bimforest.ems.modules.construction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.modules.base.service.BaseOrganizationService;
import com.bimforest.ems.modules.construction.vo.*;
import com.bimforest.ems.modules.construction.entity.WengineeringReport;
import com.bimforest.ems.modules.construction.mapper.IndexMapper;
import com.bimforest.ems.modules.construction.mapper.WengineeringReportMachineMapper;
import com.bimforest.ems.modules.construction.mapper.WengineeringReportMapper;
import com.bimforest.ems.modules.construction.mapper.WengineeringReportPersonnelMapper;
import com.bimforest.ems.modules.construction.service.WengineeringReportMachineService;
import com.bimforest.ems.modules.construction.service.WengineeringReportPersonnelService;
import com.bimforest.ems.modules.construction.service.WengineeringReportService;
import com.bimforest.ems.pojo.DateWeekly;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-28
 */
@Slf4j
@Service
public class WengineeringReportServiceImpl extends ServiceImpl<WengineeringReportMapper, WengineeringReport> implements WengineeringReportService {
    @Autowired
    WengineeringReportMapper reportMapper;
    @Autowired
    WengineeringReportMachineMapper reportMachine;
    @Autowired
    WengineeringReportMachineService machineService;
    @Autowired
    WengineeringReportPersonnelMapper personnelMapper;
    @Autowired
    WengineeringReportPersonnelService personnelService;
    @Autowired
    IndexMapper indexMapper;

    @Autowired
    BaseOrganizationService organizationService;
    /**
     * 新建--编辑基本信息
     *
     * @param baseInfoVO
     * @return
     */
    @Override
    public String insertBaseInfo(WeeklyBaseInfoVO baseInfoVO) throws ParseException {

        WengineeringReport report = new WengineeringReport();
        report.setId(baseInfoVO.getId());
        report.setOrganizationId(baseInfoVO.getOrganizationId());//关联项目id
        report.setConstructionName(baseInfoVO.getConstructionName());//项目名称
        report.setConstructionUnit(baseInfoVO.getConstructionUnit());//建设单位
        report.setQualityInspectionUnit(baseInfoVO.getQualityInspectionUnit());//质监单位
        report.setQualityInspectionStation(baseInfoVO.getQualityInspectionStation());//质监站
        report.setProjecLeader(baseInfoVO.getProjecLeader());//项目经理
        report.setTechnicalLeader(baseInfoVO.getTechnicalLeader());//技术负责人
        report.setSafeMan(baseInfoVO.getSafeMan());//安全员
        report.setContractPrice(baseInfoVO.getContractPrice());//合同总价
        report.setMoneyBack(baseInfoVO.getMoneyBack());//已经回款,已收款
        report.setWeeklyNum(baseInfoVO.getWeeklyNum());//周次
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        report.setConstructionDateStart(format.parse(baseInfoVO.getConstructionDateStart()));//施工开始日期
        report.setConstructionDateFinis(format.parse(baseInfoVO.getConstructionDateFinis()));//施工结束日期
        report.setReportBy(baseInfoVO.getReportBy());//报送者
        report.setCompletionRate(baseInfoVO.getCompletionRate());//完工率
        //获取组织名称
        if (!baseInfoVO.getOrganizationId().isEmpty()) {
            QueryWrapper queryWrapper = new QueryWrapper();
            queryWrapper.eq( "id",baseInfoVO.getOrganizationId());
            report.setReportName(organizationService.getOne(queryWrapper).getName()+"第"+report.getWeeklyNum()+"周报");
        }
        if (baseInfoVO.getId() == null) {
            reportMapper.insert(report);
            //获取已经创建的记录的ID
            String id = report.getId();
            return id;
        } else {
            reportMapper.updateById(report);
        }
        return StatusEnums.SUCCESS.getMessage();
    }

    /**
     * 编辑项目进展情况
     *
     * @param stageInfoVO
     * @return
     */
    @Override
    public int insertStageInfo(StageInfoVO stageInfoVO) {
        WengineeringReport report = new WengineeringReport();
        report.setId(stageInfoVO.getId());//周报ID
        report.setCoordination(stageInfoVO.getCoordination());//需要领导协调事项
        report.setDrawings(stageInfoVO.getDrawings());//图纸设计情况
        report.setContract(stageInfoVO.getContract());//商务合同
        report.setCivilengineeringSub(stageInfoVO.getCivilengineeringSub());//土建分项
        report.setPrefabricatedSub(stageInfoVO.getPrefabricatedSub());//装配施工分项
        report.setWaterpowerSub(stageInfoVO.getWaterpowerSub());//水电施工分项
        report.setHavcSub(stageInfoVO.getHavcSub());//暖通施工分项
        report.setDecorationSub(stageInfoVO.getDecorationSub());//装饰施工风险
        report.setOtherSub(stageInfoVO.getOtherSub());//其他附属工程
        report.setStageInfoRemark(stageInfoVO.getStageInfoRemark());//项目进展情况备注
        report.setRemark(stageInfoVO.getStageInfoRemark());//基本信息备注
        personnelMapper.deleteByWeeklyId(stageInfoVO.getId());//根据周报ID删除旧信息
        personnelService.saveBatch(stageInfoVO.getPersonnel());//批量保存施工人员信息
        reportMachine.deleteByWeeklyId(stageInfoVO.getId());//根据周报ID删除旧信息
        machineService.saveBatch(stageInfoVO.getMachine());//批量保存施工设备信息


        int i = reportMapper.updateById(report);
        return i;
    }

    /**
     * 下周计划及安排
     *
     * @param stageInfoVO
     * @return
     */
    @Override
    public int insertNextPlan(StageInfoVO stageInfoVO) {
        WengineeringReport report = new WengineeringReport();
        report.setId(stageInfoVO.getId());//周报ID
        report.setNextCoordination(stageInfoVO.getCoordination());//需要领导协调事项
        report.setNextDrawings(stageInfoVO.getDrawings());//图纸设计情况
        report.setNextContract(stageInfoVO.getContract());//商务合同
        report.setNextCivilengineeringSub(stageInfoVO.getCivilengineeringSub());//土建分项
        report.setNextPrefabricatedSub(stageInfoVO.getPrefabricatedSub());//装配施工分项
        report.setNextWaterpowerSub(stageInfoVO.getWaterpowerSub());//水电施工分项
        report.setNextHavcSub(stageInfoVO.getHavcSub());//暖通施工分项
        report.setNextDecorationSub(stageInfoVO.getDecorationSub());//装饰施工风险
        report.setNextOtherSub(stageInfoVO.getOtherSub());//其他附属工程
        report.setNextStageInfoRemark(stageInfoVO.getStageInfoRemark());//下周项目进展情况备注

        personnelMapper.deleteByNextWeeklyId(stageInfoVO.getId());//根据周报ID删除旧信息
        personnelService.saveBatch(stageInfoVO.getPersonnel());//批量保存施工人员信息

        reportMachine.deleteByNextWeeklyId(stageInfoVO.getId());//根据周报ID删除旧信息
        machineService.saveBatch(stageInfoVO.getMachine());//批量保存施工设备信息


        int i = reportMapper.updateById(report);
        return i;
    }

    /**
     * 现场整改纪要
     *
     * @param siteRectifyVO
     * @return
     */
    @Override
    public int insertSiteRectify(SiteRectifyVO siteRectifyVO) {
        WengineeringReport report = new WengineeringReport();
        report.setId(siteRectifyVO.getId());//周报ID
        report.setRectification(siteRectifyVO.getRectification());//现场整改纪要
        int i = reportMapper.updateById(report);
        return i;
    }

    /**
     * 安全生产情况
     *
     * @param safeInfoVO
     * @return
     */
    @Override
    public int insertSafeInfo(SafeInfoVO safeInfoVO) {

        WengineeringReport report = new WengineeringReport();
        report.setId(safeInfoVO.getId());//周报ID
        report.setSafeInfo(safeInfoVO.getSafeInfo());//安全生产情况
        report.setDangerSource(safeInfoVO.getDangerSource());//本周重大危险源
        report.setNextDangerSource(safeInfoVO.getNextDangerSource());//下周重大危险源
        report.setSiteCheck(safeInfoVO.getSiteCheck());//本周现场检查情况
        int i = reportMapper.updateById(report);
        return i;
    }

    /**
     * 本周收发资料汇总
     *
     * @param inOutFileVO
     * @return
     */
    @Override
    public int insertInOutFile(InOutFileVO inOutFileVO) {
        WengineeringReport report = new WengineeringReport();
        report.setId(inOutFileVO.getId());//周报ID
        report.setReceiveFileIn(inOutFileVO.getReceiveFileIn());//收文件
        report.setReceiveFileOut(inOutFileVO.getReceiveFileOut());//发文件
        int i = reportMapper.updateById(report);
        return i;
    }

    /**
     * 可能影响工程的因素
     *
     * @param effectVO
     * @return
     */
    @Override
    public int insertEffect(EffectVO effectVO) {
        WengineeringReport report = new WengineeringReport();
        report.setId(effectVO.getId());//周报ID
        report.setEffect(effectVO.getEffect());//可能影响工程的因素
        int i = reportMapper.updateById(report);
        return i;
    }

    /**
     * 周报--通过周报ID 获取周报基本信息
     *
     * @param id
     * @return
     */
    @Override
    public WeeklyBaseInfoVO getBaseInfoById(String id) {
        return reportMapper.getBaseInfoById(id);
    }

    /**
     * 获取进展信息
     *
     * @param id
     * @return
     */
    @Override
    public StageInfoVO getStageInfoById(String id) {
        return reportMapper.getStageInfoById(id);
    }

    /**
     * 获取下周项目进展信息
     *
     * @param id
     * @return
     */
    @Override
    public StageInfoVO getNextPlan(String id) {
        return reportMapper.getNextPlanById(id);
    }

    /**
     * 获取现场整改纪要
     *
     * @param id
     * @return
     */
    @Override
    public SiteRectifyVO getSiteRectify(String id) {
        return reportMapper.getSiteRectify(id);
    }

    /**
     * 获取安全生产情况
     *
     * @param id
     * @return
     */
    @Override
    public SafeInfoVO getSafeInfo(String id) {
        return reportMapper.getSafeInfo(id);
    }

    /**
     * 获取本周收发资料汇总
     *
     * @param id
     * @return
     */
    @Override
    public InOutFileVO getInOutFile(String id) {
        return reportMapper.getInOutFile(id);
    }

    /**
     * 获取可能影响工程的因素
     *
     * @param id
     * @return
     */
    @Override
    public EffectVO getEffect(String id) {
        return reportMapper.getEffect(id);
    }

    /**
     * 获取现场其他需要的说明
     *
     * @param id
     * @return
     */
    @Override
    public SiteOtherInfoVO getSiteOtherInfo(String id) {
        return reportMapper.getSiteOtherInfo(id);
    }

    /**
     * 提交报表
     *
     * @param submitReport
     * @return
     */
    @Override
    public int submitReport(SubmitReport submitReport) {
        WengineeringReport report = new WengineeringReport();
        log.info("---" + submitReport.getWeeklyId());
        report.setId(submitReport.getWeeklyId());
        report.setState(StatusEnums.SUBMIT.getCode());
        return reportMapper.updateById(report);
    }

    /**
     * 填写辅助--获取周次ID,通过周次
     *
     * @param weekly
     * @return
     */
    @Override
    public String getWeeklyIdByWeekly(Integer weekly, String engineeringId) {
        return reportMapper.getWeeklyIdByWeekly(weekly, engineeringId);
    }

    /**
     * 填写辅助--获取上填写的,下周计划
     *
     * @param weekly
     * @return
     */
    @Override
    public StageInfoVO getNextStageInfo(Integer weekly, String engineeringId) {
        return reportMapper.getNextStageInfo(weekly, engineeringId);
    }

    /**
     * 周报必填字段查询
     *
     * @param id
     * @return
     */
    @Override
    public WeeklyMustFiledVO selectMustInfo(String id) {

        return reportMapper.reportMapper(id);
    }


    /**
     * 现场其他需要说明
     *
     * @param otherInfoVO
     * @return
     */
    @Override
    public int insertSiteOtherInfo(SiteOtherInfoVO otherInfoVO) {
        WengineeringReport report = new WengineeringReport();
        report.setId(otherInfoVO.getId());//周报ID
        report.setOtherSiteInfo(otherInfoVO.getOtherSiteInfo());//可能影响工程的因素
        int i = reportMapper.updateById(report);
        return i;
    }

    /**
     * 查询本年度的所有周次记录
     *
     * @param engineeringId
     * @return
     */
    @Override
    public List<DateWeekly> selectListWeeklyById(String engineeringId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        //查询当年的所有记录
        Date nowDate = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy");
        String year = format.format(nowDate);
        Integer years = Integer.valueOf(year);
        List<DateWeekly> integers = reportMapper.selectYearItemList(years);
        return integers;
    }


}

package com.bimforest.ems.modules.construction.service.impl;

import com.bimforest.ems.common.constant.CommonConstant;
import com.bimforest.ems.modules.construction.vo.ImageProgressVO;
import com.bimforest.ems.modules.construction.vo.InsertImageProgressVO;
import com.bimforest.ems.modules.construction.entity.MengineeringImageProgress;
import com.bimforest.ems.modules.construction.mapper.MengineeringImageProgressMapper;
import com.bimforest.ems.modules.construction.service.MengineeringImageProgressService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-09
 */
@Service
public class MengineeringImageProgressServiceImpl extends ServiceImpl<MengineeringImageProgressMapper, MengineeringImageProgress> implements MengineeringImageProgressService {
    @Autowired
    MengineeringImageProgressMapper progressMapper;

    /**
     * 插入形象进度表
     *
     * @param imageProgress
     * @return
     */
    @Override
    public int insetImageProgress(@RequestBody InsertImageProgressVO imageProgress) {
        MengineeringImageProgress progress = new MengineeringImageProgress();
        progress.setReportType(CommonConstant.PROGRESS);//设置报表类型为形象进度表
        progress.setMonth_num(imageProgress.getMonth());//月份
        progress.setEngineeringId(imageProgress.getEngineeringId());//项目ID
        int insert = progressMapper.insert(progress);
        return insert;
    }

    /**
     * 更新形象进度表
     *
     * @param progress
     * @return
     */
    @Override
    public int updateImageProgress(ImageProgressVO progress) {
        MengineeringImageProgress imageProgress = new MengineeringImageProgress();
        imageProgress.setId(progress.getId());//报表ID
        imageProgress.setEngineeringId(progress.getEngineeringId());//项目ID
        imageProgress.setMonth_num(progress.getMonthNum());//月份
        imageProgress.setEngName(progress.getEngName());//工程名称
        imageProgress.setProjectLeader(progress.getProjectLeader());//项目经理
        imageProgress.setFloorArea(progress.getFloorArea());//建筑面积
        imageProgress.setContractPrice(progress.getContractPrice());//合同价
        imageProgress.setStartDate(progress.getStartDate());//开始日期
        imageProgress.setEngName(progress.getEngName());//结束日期
        imageProgress.setCompletionRate(progress.getCompletionRate());//完工率
        imageProgress.setMonthOutputValue(progress.getMonthOutputValue());//每月产值
        imageProgress.setEditBy(progress.getEditBy());//编制人
        imageProgress.setAuditorBy(progress.getAuditorBy());//审核人
        imageProgress.setCurrentContractProgress(progress.getCurrentContractProgress());//合同形象进度
        imageProgress.setCurrentCurrentProgress(progress.getCurrentCurrentProgress());//本期形象进度
        imageProgress.setNextProgressPlan(progress.getNextProgressPlan());//下月施工进度计划
        imageProgress.setSecurityQualityProblem(progress.getSecurityQualityProblem());//安全及质量问题
        imageProgress.setSiteInstallationPersonne(progress.getSiteInstallationPersonne());//现场安装人员
        int updateById = progressMapper.updateById(imageProgress);
        return updateById;
    }

    /**
     * 获取未使用月份
     *
     * @param organizationId
     * @return
     */
    @Override
    public List<Integer> selectMonthByEngineering(String organizationId) {
        //设置12 个月
        List<Integer> months = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            months.add(i);
        }
        String year = String.valueOf(Calendar.getInstance().get(Calendar.YEAR));
        //查询当年份已有月份
        List<Integer> currentMonth = progressMapper.getCurrentMonth(year,organizationId);

        months.removeAll(currentMonth);

        return months;
    }

    /**
     * 获取单条信息
     *
     * @param id
     * @return
     */
    @Override
    public InsertImageProgressVO selectOneImageProgress(String id) {

        return null;
    }
}

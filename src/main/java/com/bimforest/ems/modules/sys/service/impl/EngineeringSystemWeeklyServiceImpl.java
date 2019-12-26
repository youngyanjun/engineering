package com.bimforest.ems.modules.sys.service.impl;

import com.bimforest.ems.modules.sys.entity.EngineeringSystemWeekly;
import com.bimforest.ems.modules.sys.mapper.EngineeringSystemWeeklyMapper;
import com.bimforest.ems.modules.sys.service.EngineeringSystemWeeklyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.SystemWeeklyVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
@Service
public class EngineeringSystemWeeklyServiceImpl extends ServiceImpl<EngineeringSystemWeeklyMapper, EngineeringSystemWeekly> implements EngineeringSystemWeeklyService {
    @Autowired
    EngineeringSystemWeeklyMapper weeklyMapper;
    @Override
    public List<SystemWeeklyVO> selectList() {
        return weeklyMapper.selectWeeklyList();
    }
}

package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.EngineeringSystemWeekly;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.SystemWeeklyVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
public interface EngineeringSystemWeeklyService extends IService<EngineeringSystemWeekly> {

    List<SystemWeeklyVO> selectList();
}

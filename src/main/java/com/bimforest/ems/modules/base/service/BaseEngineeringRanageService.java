package com.bimforest.ems.modules.base.service;

import com.bimforest.ems.modules.base.vo.EngineeringRanageSelectVO;
import com.bimforest.ems.modules.base.entity.BaseEngineeringRanage;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-21
 */
public interface BaseEngineeringRanageService extends IService<BaseEngineeringRanage> {

    List<EngineeringRanageSelectVO> selectEngineeringRanages();

     int delete(String engineeringCode);

    List<BaseEngineeringRanage> selectList(String id);

    int updateMamagerId(String id, String engineeringCode);




}

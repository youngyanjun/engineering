package com.bimforest.ems.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.base.entity.BaseEngineeringSubcontractor;
import com.bimforest.ems.modules.sys.vo.EngineeringInvolveSubcontractVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
public interface BaseSubcontractorService extends IService<BaseEngineeringSubcontractor> {

    int delete(String engineeringCode);

    List<EngineeringInvolveSubcontractVO> selectSubcontractList(String engineeringId);

    int updateMamagerId(String id, String engineeringCode);
}

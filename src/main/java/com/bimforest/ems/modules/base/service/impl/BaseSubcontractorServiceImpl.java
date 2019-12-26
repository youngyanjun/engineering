package com.bimforest.ems.modules.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bimforest.ems.modules.base.entity.BaseEngineeringSubcontractor;
import com.bimforest.ems.modules.sys.mapper.BaseEngineeringSubcontractorMapper;
import com.bimforest.ems.modules.base.service.BaseSubcontractorService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.EngineeringInvolveSubcontractVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
@Service
public class BaseSubcontractorServiceImpl extends ServiceImpl<BaseEngineeringSubcontractorMapper, BaseEngineeringSubcontractor> implements BaseSubcontractorService {
    @Autowired
    BaseEngineeringSubcontractorMapper subcontractMapper;
    @Override
    public int delete(String engineeringCode) {
        return subcontractMapper.deletes(engineeringCode);
    }

    @Override
    public List<EngineeringInvolveSubcontractVO> selectSubcontractList(String engineeringId) {
        return subcontractMapper.selectSubcontractList(engineeringId);
    }

    @Override
    public int updateMamagerId(String id, String engineeringCode) {
        BaseEngineeringSubcontractor subcontract = new BaseEngineeringSubcontractor();
        UpdateWrapper updateWrapper = new UpdateWrapper();
        subcontract.setEngineeringId(id);
        updateWrapper.eq( "engineering_id", engineeringCode);
        int update = subcontractMapper.update(subcontract, updateWrapper);
        return update;
    }
}

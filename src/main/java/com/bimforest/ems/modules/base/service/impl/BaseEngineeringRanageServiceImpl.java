package com.bimforest.ems.modules.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.bimforest.ems.modules.base.vo.EngineeringRanageSelectVO;
import com.bimforest.ems.modules.base.entity.BaseEngineeringRanage;
import com.bimforest.ems.modules.base.mapper.BaseEngineeringRanageMapper;
import com.bimforest.ems.modules.base.service.BaseEngineeringRanageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-21
 */
@Service
public class BaseEngineeringRanageServiceImpl extends ServiceImpl<BaseEngineeringRanageMapper, BaseEngineeringRanage> implements BaseEngineeringRanageService {

    @Override
    public List<EngineeringRanageSelectVO> selectEngineeringRanages() {
        return baseMapper.selectEngineeringRanages();
    }

    @Override
    public int delete(String engineeringCode) {

        return  baseMapper.deleteList(engineeringCode);
    }

    @Override
    public List<BaseEngineeringRanage> selectList(String id) {

        return baseMapper.selectRanageList(id);
    }

    /**
     * 修改managerID
     * @param id
     * @param engineeringCode
     * @return
     */
    @Override
    public int updateMamagerId(String id, String engineeringCode) {
        BaseEngineeringRanage ranage = new BaseEngineeringRanage();
        ranage.setEngineeringId(id);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        updateWrapper.eq( "engineering_id",engineeringCode);
        int update = baseMapper.update(ranage,updateWrapper);
        return update;
    }
}

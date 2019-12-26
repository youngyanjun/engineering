package com.bimforest.ems.modules.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.base.entity.BaseEngineering;
import com.bimforest.ems.modules.sys.mapper.BaseEngineeringMapper;
import com.bimforest.ems.modules.base.service.BaseEngineeringService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.EngineeringAddVO;
import com.bimforest.ems.modules.sys.vo.EngineeringListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 项目管理表 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-12
 */
@Service
public class BaseEngineeringServiceImpl extends ServiceImpl<BaseEngineeringMapper, BaseEngineering> implements BaseEngineeringService {
    @Autowired
    BaseEngineeringMapper baseEngineeringMapper;

    @Override
    public IPage<EngineeringListVO> selectEngineeringList(Page page, String engineeringName) {

        return baseEngineeringMapper.selectEngineeringList(page, engineeringName);
    }

    /**
     * 插入项目基本信息
     *
     * @param engineeringAddVO
     * @return
     */
    @Override
    public int insertBaseInfo(EngineeringAddVO engineeringAddVO) {
        System.out.println("----------" + engineeringAddVO.getContentPrice());
        BaseEngineering engineeringManager = new BaseEngineering();
        engineeringManager.setEngineeringCode(engineeringAddVO.getEngineeringCode());
        engineeringManager.setEngineeringName(engineeringAddVO.getEngineeringName());
        engineeringManager.setEngineeringAbbreviation(engineeringAddVO.getEngineeringAbbreviation());
        engineeringManager.setManagerId(engineeringAddVO.getManagerId());
        engineeringManager.setContractPrice(engineeringAddVO.getContentPrice());
        engineeringManager.setOrgId(engineeringAddVO.getOrgId());
        return baseEngineeringMapper.insert(engineeringManager);
    }

    /**
     * 查找当前项目编码是否存在
     * @param engineeringCode
     * @return
     */
    @Override
    public int selectEngineeringCode(String engineeringCode) {
        int i = baseEngineeringMapper.selectEngineeringCode(engineeringCode);
        return i;
    }

    /**
     * 查找非当前项目编码是否存在
     * @param engineeringCode
     * @return
     */
    @Override
    public int selectOtherEngineeringCode(String id,String engineeringCode) {
        int i = baseEngineeringMapper.selectOtherEngineeringCode(id, engineeringCode);
        return i;
    }

    @Override
    public BaseEngineering selectId(String engineeringCode) {
        BaseEngineering manager = new BaseEngineering();
        QueryWrapper wrapper = new QueryWrapper();
        wrapper.eq(true, "engineering_code", engineeringCode);
        return baseEngineeringMapper.selectOne(wrapper);
    }

    /**
     * 修改orgId 为""
     * @param id
     */
    @Override
    public void updateOrgIdById(String id) {
        baseEngineeringMapper.updateOrgIdById(id);
    }

    @Override
    public BaseEngineering selectOne(String id) {
        baseEngineeringMapper.selectById(id);
        return baseEngineeringMapper.selectById(id);
    }


}

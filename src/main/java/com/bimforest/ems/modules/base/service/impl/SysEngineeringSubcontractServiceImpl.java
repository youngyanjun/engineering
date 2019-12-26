package com.bimforest.ems.modules.base.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.base.entity.BaseSubcontractor;
import com.bimforest.ems.modules.sys.mapper.BaseSubcontractorMapper;
import com.bimforest.ems.modules.sys.service.SysEngineeringSubcontractService;
import com.bimforest.ems.modules.sys.vo.SubcontractorListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 分包商表 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
@Service
public class SysEngineeringSubcontractServiceImpl extends ServiceImpl<BaseSubcontractorMapper, BaseSubcontractor> implements SysEngineeringSubcontractService {
    @Autowired
    BaseSubcontractorMapper sysEngineeringSubcontractMapper;
    @Override
    public IPage<SubcontractorListVO> selectList(Page page, String subcontractorName) {


        return sysEngineeringSubcontractMapper.selectSubcontractList(page,subcontractorName);
    }

    @Override
    public List selectSubcontractList() {

        return sysEngineeringSubcontractMapper.selectSubcontract();
    }

    @Override
    public void deleteById(String id) {
        sysEngineeringSubcontractMapper.deleteById(id);
    }


}

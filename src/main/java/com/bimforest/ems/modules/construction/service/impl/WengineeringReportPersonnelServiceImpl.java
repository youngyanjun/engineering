package com.bimforest.ems.modules.construction.service.impl;

import com.bimforest.ems.modules.construction.entity.WengineeringReportPersonnel;
import com.bimforest.ems.modules.construction.mapper.WengineeringReportPersonnelMapper;
import com.bimforest.ems.modules.construction.service.WengineeringReportPersonnelService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工程报表周报---施工人员 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-03
 */
@Service
public class WengineeringReportPersonnelServiceImpl extends ServiceImpl<WengineeringReportPersonnelMapper, WengineeringReportPersonnel> implements WengineeringReportPersonnelService {
   @Autowired
   WengineeringReportPersonnelMapper personnelMapper;
    /**
     * 获取施工人员
     * @param id
     * @return
     */
    @Override
    public List<WengineeringReportPersonnel> PersonnelList(String id ,String type) {
        return personnelMapper.getPersonnelList(id,type);
    }
}

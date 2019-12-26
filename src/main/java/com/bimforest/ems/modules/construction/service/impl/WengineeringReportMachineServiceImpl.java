package com.bimforest.ems.modules.construction.service.impl;

import com.bimforest.ems.modules.construction.entity.WengineeringReportMachine;
import com.bimforest.ems.modules.construction.mapper.WengineeringReportMachineMapper;
import com.bimforest.ems.modules.construction.service.WengineeringReportMachineService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 工程报表周报---施工机械 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-03
 */
@Service
public class WengineeringReportMachineServiceImpl extends ServiceImpl<WengineeringReportMachineMapper, WengineeringReportMachine> implements WengineeringReportMachineService {
    @Autowired
    WengineeringReportMachineMapper machineMapper;
    /**
     * 获取施工及机械
     * @param id
     * @return
     */
    @Override
    public List<WengineeringReportMachine> machineList(String id,String type) {
        return machineMapper.getMachineList(id,type);
    }
}

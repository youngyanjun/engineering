package com.bimforest.ems.modules.construction.service;

import com.bimforest.ems.modules.construction.entity.WengineeringReportMachine;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工程报表周报---施工机械 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-03
 */
public interface WengineeringReportMachineService extends IService<WengineeringReportMachine> {


    List<WengineeringReportMachine> machineList(String id,String type);


}

package com.bimforest.ems.modules.construction.service;

import com.bimforest.ems.modules.construction.entity.WengineeringReportPersonnel;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 工程报表周报---施工人员 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-03
 */
public interface WengineeringReportPersonnelService extends IService<WengineeringReportPersonnel> {
    List<WengineeringReportPersonnel> PersonnelList(String id,String type);
}

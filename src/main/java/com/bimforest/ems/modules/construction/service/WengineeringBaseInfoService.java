package com.bimforest.ems.modules.construction.service;

import com.bimforest.ems.modules.construction.entity.WengineeringBaseInfo;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 工程报表—周报 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-06
 */
public interface WengineeringBaseInfoService extends IService<WengineeringBaseInfo> {

    WengineeringBaseInfo selectOne(String engineeringId);

}

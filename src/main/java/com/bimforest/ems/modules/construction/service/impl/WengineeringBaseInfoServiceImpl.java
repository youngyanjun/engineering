package com.bimforest.ems.modules.construction.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bimforest.ems.modules.construction.entity.WengineeringBaseInfo;
import com.bimforest.ems.modules.construction.mapper.WengineeringBaseInfoMapper;
import com.bimforest.ems.modules.construction.service.WengineeringBaseInfoService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 工程报表—周报 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-06
 */
@Service
public class WengineeringBaseInfoServiceImpl extends ServiceImpl<WengineeringBaseInfoMapper, WengineeringBaseInfo> implements WengineeringBaseInfoService {

    @Autowired
    WengineeringBaseInfoMapper baseInfoMapper;

    @Override
    public WengineeringBaseInfo selectOne(String engineeringId) {

        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq(StringUtils.isNotEmpty(engineeringId), "engineering_id", engineeringId);

        return baseInfoMapper.selectOne(queryWrapper);
    }
}

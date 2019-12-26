package com.bimforest.ems.modules.base.service.impl;

import com.bimforest.ems.modules.base.entity.BaseEngineeringSingle;
import com.bimforest.ems.modules.base.mapper.BaseEngineeringSingleMapper;
import com.bimforest.ems.modules.base.service.BaseEngineeringSingleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-23
 */
@Service
public class BaseEngineeringSingleServiceImpl extends ServiceImpl<BaseEngineeringSingleMapper, BaseEngineeringSingle> implements BaseEngineeringSingleService {

    @Override
    public int delete(List<String> ids, String updateUser) {
        return baseMapper.delete(ids, updateUser);
    }
}

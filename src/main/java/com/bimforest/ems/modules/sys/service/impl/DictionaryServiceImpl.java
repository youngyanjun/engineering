package com.bimforest.ems.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.bimforest.ems.modules.sys.entity.Dictionary;
import com.bimforest.ems.modules.sys.mapper.DictionaryMapper;
import com.bimforest.ems.modules.sys.service.DictionaryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-11
 */
@Service
public class DictionaryServiceImpl extends ServiceImpl<DictionaryMapper, Dictionary> implements DictionaryService {
    @Autowired
    DictionaryMapper dictionaryMapper;
    @Override
    public Dictionary selectOne(String id) {
        QueryWrapper q = new QueryWrapper();
        q.eq(StringUtils.isNotEmpty(id), "id", id);
        dictionaryMapper.selectOne(q);
        return dictionaryMapper.selectOne(q);
    }
}

package com.bimforest.ems.modules.construction.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.construction.vo.IndexReportVO;
import com.bimforest.ems.modules.construction.entity.Index;
import com.bimforest.ems.modules.construction.mapper.IndexMapper;
import com.bimforest.ems.modules.construction.service.IndexService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-04
 */
@Service
public class IndexServiceImpl extends ServiceImpl<IndexMapper, Index> implements IndexService {
    @Autowired
    IndexMapper indexMapper;
    /**
     * 周报索引
     * @param page
     * @param type
     * @param name
     * @return
     */
    @Override
    public IPage<IndexReportVO> selectList(Page page,String organizationId, String type, String name) {
        return  indexMapper.selectReportList(page,organizationId,type,name);
    }
}

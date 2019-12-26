package com.bimforest.ems.modules.construction.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.construction.vo.IndexReportVO;
import com.bimforest.ems.modules.construction.entity.Index;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-04
 */
public interface IndexService extends IService<Index> {

    IPage<IndexReportVO> selectList(Page page,String organizationId,String type, String name);
}

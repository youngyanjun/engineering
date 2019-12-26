package com.bimforest.ems.modules.construction.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.construction.vo.IndexReportVO;
import com.bimforest.ems.modules.construction.entity.Index;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-04
 */
public interface IndexMapper extends BaseMapper<Index> {



    IPage<IndexReportVO> selectReportList(Page page,@Param("organizationId") String organizationId,@Param("type") String type, @Param("name") String name);

}

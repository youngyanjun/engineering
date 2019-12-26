package com.bimforest.ems.modules.sys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.base.entity.BaseSubcontractor;
import com.bimforest.ems.modules.sys.vo.SubcontractorEngineeringVO;
import com.bimforest.ems.modules.sys.vo.SubcontractorListVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 分包商表 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
public interface BaseSubcontractorMapper extends BaseMapper<BaseSubcontractor> {

    IPage<SubcontractorListVO> selectSubcontractList(Page page, @Param("name") String name);

    List<SubcontractorEngineeringVO> selectSubcontract();
}

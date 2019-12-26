package com.bimforest.ems.modules.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.base.entity.BaseSubcontractor;
import com.bimforest.ems.modules.sys.vo.SubcontractorListVO;

import java.util.List;

/**
 * <p>
 * 分包商表 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
public interface SysEngineeringSubcontractService extends IService<BaseSubcontractor> {


    //查询分包商列表
    IPage<SubcontractorListVO> selectList(Page page, String subcontractorrName);
    //项目管理查询分包商列表
    List selectSubcontractList();

    /**
     * 删除分包商
     * @param id
     */
    void deleteById(String id);
}

package com.bimforest.ems.modules.base.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.base.entity.BaseEngineering;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.EngineeringAddVO;
import com.bimforest.ems.modules.sys.vo.EngineeringListVO;

/**
 * <p>
 * 项目管理表 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-12
 */
public interface BaseEngineeringService extends IService<BaseEngineering> {

    IPage<EngineeringListVO> selectEngineeringList(Page page, String engineeringName);

    //插入基本信息
    int insertBaseInfo(EngineeringAddVO engineeringAddVO);

    int selectEngineeringCode(String engineeringCode);
    //查找非当前项目编码是否存在
    int selectOtherEngineeringCode(String id,String engineeringCode);

    BaseEngineering selectId(String engineeringCode);

    void updateOrgIdById(String id);

    BaseEngineering selectOne(String id);
}

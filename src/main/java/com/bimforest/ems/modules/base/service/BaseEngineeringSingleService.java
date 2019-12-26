package com.bimforest.ems.modules.base.service;

import com.bimforest.ems.modules.base.entity.BaseEngineeringSingle;
import com.baomidou.mybatisplus.extension.service.IService;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-23
 */
public interface BaseEngineeringSingleService extends IService<BaseEngineeringSingle> {

    int delete(List<String> ids, String updateUser);
}

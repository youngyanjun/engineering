package com.bimforest.ems.modules.base.service;

import com.bimforest.ems.modules.base.entity.UserOrganization;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-10
 */
public interface UserOrganizationService extends IService<UserOrganization> {


    List<String> selectList(String organizationId);
}

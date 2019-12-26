package com.bimforest.ems.modules.base.service.impl;

import com.bimforest.ems.modules.base.entity.UserOrganization;
import com.bimforest.ems.modules.base.mapper.UserOrganizationMapper;
import com.bimforest.ems.modules.base.service.UserOrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-10
 */
@Service
public class UserOrganizationServiceImpl extends ServiceImpl<UserOrganizationMapper, UserOrganization> implements UserOrganizationService {
    @Autowired
    UserOrganizationMapper organizationMapper;
    /**
     * 查找当前组织用户列表
     * @param organizationId
     * @return
     */
    @Override
    public List<String> selectList(String organizationId) {

        return organizationMapper.selectUserList(organizationId);
    }


}

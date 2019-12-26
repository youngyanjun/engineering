package com.bimforest.ems.modules.base.service;

import com.bimforest.ems.modules.base.vo.BaseInfoVO;
import com.bimforest.ems.modules.base.entity.BaseOrganization;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.OrganizationListVO;
import com.bimforest.ems.modules.sys.vo.OrganizationTemplateListVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
public interface BaseOrganizationService extends IService<BaseOrganization> {

    List<BaseOrganization> selectOrganizationsByUserId(String userId);

    //自动添加‘组织管理’
    int insertOrganization(BaseOrganization baseOrganization);

    List<OrganizationListVO> selectOrganizationList(String name);

    int updateAddress(String id,String longitude, String latitude);

    boolean selectOne(String id);

   List<OrganizationTemplateListVO>  getUserOrganizationByUserId(String engineeringId);

    void deleteById(String id);

    BaseInfoVO selectBaseInfoOne(String orgId);

}

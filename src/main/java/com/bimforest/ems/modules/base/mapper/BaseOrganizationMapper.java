package com.bimforest.ems.modules.base.mapper;

import com.bimforest.ems.modules.base.vo.BaseInfoVO;
import com.bimforest.ems.modules.base.entity.BaseOrganization;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.OrganizationListVO;
import com.bimforest.ems.modules.sys.vo.OrganizationTemplateListVO;
import io.lettuce.core.dynamic.annotation.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
public interface BaseOrganizationMapper extends BaseMapper<BaseOrganization> {

    List<OrganizationListVO> selectOrganizationList( @Param("organizationName") String organizationName );

    int updateAddress(String id,String longitude, String latitude);

    List<OrganizationTemplateListVO> getUserOrganizationByUserId(@Param("engineeringId") String engineeringId);

    int insertBatch(@Param("list") List list,@Param("id") String managerId);

    List<BaseOrganization> selectOrganizationsByUserId(String userId);

    BaseInfoVO selectBaseInfoOne(String orgId);

}

package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.sys.entity.EngineeringOrganizationTemplate;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.OrganizationTemplateListVO;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
public interface EngineeringOrganizationTemplateMapper extends BaseMapper<EngineeringOrganizationTemplate> {

    List<OrganizationTemplateListVO> selectListTemplate();

}

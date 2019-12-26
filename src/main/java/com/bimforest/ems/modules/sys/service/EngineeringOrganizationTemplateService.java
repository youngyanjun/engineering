package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.EngineeringOrganizationTemplate;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.OrganizationTemplateListVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
public interface EngineeringOrganizationTemplateService extends IService<EngineeringOrganizationTemplate> {

    List<OrganizationTemplateListVO> selectOrganizationTemplateList();

    int deleteorganizationTemplate(String id);

    List<OrganizationTemplateListVO> selectOrganizationTemplateLists();

}

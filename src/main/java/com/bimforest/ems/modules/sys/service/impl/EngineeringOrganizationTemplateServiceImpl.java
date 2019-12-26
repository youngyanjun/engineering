package com.bimforest.ems.modules.sys.service.impl;

import com.bimforest.ems.common.utils.TreeNodeUtil;
import com.bimforest.ems.modules.sys.entity.EngineeringOrganizationTemplate;
import com.bimforest.ems.modules.sys.mapper.EngineeringOrganizationTemplateMapper;
import com.bimforest.ems.modules.sys.service.EngineeringOrganizationTemplateService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.OrganizationTemplateListVO;
import com.bimforest.ems.modules.sys.vo.RoleListVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
@Service
public class EngineeringOrganizationTemplateServiceImpl extends ServiceImpl<EngineeringOrganizationTemplateMapper, EngineeringOrganizationTemplate> implements EngineeringOrganizationTemplateService {
    @Autowired
    EngineeringOrganizationTemplateMapper templateMapper;

    @Override
    public List<OrganizationTemplateListVO> selectOrganizationTemplateList() {
        List<OrganizationTemplateListVO> selectList = templateMapper.selectListTemplate();
        List<OrganizationTemplateListVO> listTree = TreeNodeUtil.assembleTree(selectList);
        return listTree;
    }

    @Override
    public int deleteorganizationTemplate(String id) {
        int i = templateMapper.deleteById(id);
        return i;
    }

    @Override
    public List<OrganizationTemplateListVO> selectOrganizationTemplateLists() {
        List<OrganizationTemplateListVO> selectList = templateMapper.selectListTemplate();
        return selectList;
    }
}

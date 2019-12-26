package com.bimforest.ems.modules.base.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bimforest.ems.common.utils.TreeNodeUtil;
import com.bimforest.ems.modules.base.vo.BaseInfoVO;
import com.bimforest.ems.modules.construction.entity.EngineeringOrganizationUser;
import com.bimforest.ems.modules.construction.service.EngineeringOrganizationUserService;
import com.bimforest.ems.modules.base.entity.BaseOrganization;
import com.bimforest.ems.modules.base.mapper.BaseOrganizationMapper;
import com.bimforest.ems.modules.base.service.BaseOrganizationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.service.EngineeringOrganizationTemplateService;
import com.bimforest.ems.modules.sys.vo.OrganizationListVO;
import com.bimforest.ems.modules.sys.vo.OrganizationTemplateListVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-13
 */
@Slf4j
@Service
public class BaseOrganizationServiceImpl extends ServiceImpl<BaseOrganizationMapper, BaseOrganization> implements BaseOrganizationService {
    @Autowired
    BaseOrganizationMapper baseOrganizationMapper;

    @Autowired
    EngineeringOrganizationTemplateService templateService;

    @Autowired
    EngineeringOrganizationUserService userService;

    /**
     * 自动添加组织机构
     *
     * @param baseOrganization
     * @return
     */
    @Override
    public int insertOrganization(BaseOrganization baseOrganization) {
        return baseOrganizationMapper.insert(baseOrganization);
    }

    /**
     * 组织机构列表
     *
     * @param name
     * @return
     */
    @Override
    public List<OrganizationListVO> selectOrganizationList(String name) {

        return baseOrganizationMapper.selectOrganizationList(name);
    }

    /**
     * 修改项目经纬度
     *
     * @param longitude
     * @param latitude
     * @return
     */
    @Override
    public int updateAddress(String id, String longitude, String latitude) {

        return baseOrganizationMapper.updateAddress(id, longitude, latitude);
    }

    @Override
    public boolean selectOne(String id) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("engineering_id", "id");
        BaseOrganization baseOrganization = baseOrganizationMapper.selectOne(queryWrapper);
        if (baseOrganization != null) {
            return true;
        }
        return false;
    }

    /**
     * 获取用户组织机构列表
     *
     * @param engineeringId
     * @return
     */
    @Override
    public List<OrganizationTemplateListVO> getUserOrganizationByUserId(String engineeringId) {
        List<OrganizationTemplateListVO> userOrganizationByUserId = baseOrganizationMapper.getUserOrganizationByUserId(engineeringId);
        if (userOrganizationByUserId.size() == 0) {
            //查出模板组织机构
            List<OrganizationTemplateListVO> listVOS = templateService.selectOrganizationTemplateLists();
            //给用户插入模板上已经存在的组织机构
            for (int i = 0; i < listVOS.size(); i++) {
                EngineeringOrganizationUser organization = new EngineeringOrganizationUser();
                organization.setEngineeringId(engineeringId);
                organization.setParentId(listVOS.get(i).getParentId());
                organization.setOrgId(listVOS.get(i).getId());
                organization.setName(listVOS.get(i).getName());
                organization.setRemark(listVOS.get(i).getRemark());
                //插入数据
                userService.save(organization);
            }
            List<OrganizationTemplateListVO> listTree = TreeNodeUtil.assembleTree(listVOS);
            return listTree;
        }
        List<OrganizationTemplateListVO> listTree = TreeNodeUtil.assembleTree(userOrganizationByUserId);
        return listTree;
    }

    /**
     * 删除组织
     * @param id
     */
    @Override
    public void deleteById(String id) {
        baseOrganizationMapper.deleteById(id);
    }

    /**
     * 得到項目基本信息
     * @param orgId
     * @return
     */
    @Override
    public BaseInfoVO selectBaseInfoOne(String orgId) {

        return  baseOrganizationMapper.selectBaseInfoOne(orgId);
    }

    @Override
    public boolean saveBatch(Collection<BaseOrganization> entityList) {
        return false;
    }

    @Override
    public List<BaseOrganization> selectOrganizationsByUserId(String userId) {
        return baseMapper.selectOrganizationsByUserId(userId);
    }

}

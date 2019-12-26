package com.bimforest.ems.modules.base.service.impl;

import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.common.utils.TreeNodeUtil;
import com.bimforest.ems.modules.base.vo.*;
import com.bimforest.ems.modules.base.entity.BaseStructure;
import com.bimforest.ems.modules.base.entity.BaseStructureUser;
import com.bimforest.ems.modules.base.mapper.BaseStructureMapper;
import com.bimforest.ems.modules.base.mapper.BaseStructureUserMapper;
import com.bimforest.ems.modules.base.service.BaseStructureService;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.utils.StrUtils;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-13
 */
@Service
public class BaseStructureServiceImpl extends ServiceImpl<BaseStructureMapper, BaseStructure> implements BaseStructureService {

    @Autowired
    BaseStructureMapper baseStructureMapper;

    @Autowired
    BaseStructureUserMapper baseStructureUserMapper;

    /**
     * @param orgId
     * @Description: 获取组织结构树
     * @author max.zhang
     * @date 2019/12/13 11:39
     */
    @Override
    public List<StructureTreeVO> selectStructureTree(String orgId) {
        List<StructureTreeVO> structureTreeVO = baseStructureMapper.queryStructureSelectVOs(orgId);
        // 排列成树形结构
        structureTreeVO = TreeNodeUtil.assembleTree(structureTreeVO);
        return structureTreeVO;
    }

    @Override
    public int addStructure(StructureAddVO structureAddVO) {
        BaseStructure baseStructure = new BaseStructure();
        BeanUtils.copyProperties(structureAddVO, baseStructure);
        return baseMapper.insert(baseStructure);
    }

    @Override
    public int updateStructure(StructureAddVO structureAddVO) {
        BaseStructure baseStructure = new BaseStructure();
        BeanUtils.copyProperties(structureAddVO, baseStructure);
        return baseMapper.updateById(baseStructure);
    }

    @Override
    public Integer deleteStructure(String id) {
        return baseMapper.deleteStructure(id);
    }

    @Override
    public int addUsersForStructure(UserStructureAddVO userStructureAddVO) {

        if (CollectionUtil.isNotEmpty(userStructureAddVO.getUserIds()) && StrUtils.isNotBlank(userStructureAddVO.getStructureId())) {
            List<BaseStructureUser> baseStructureUsers = new LinkedList<>();
            List<String> userIds = userStructureAddVO.getUserIds();
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            BaseStructureUser baseStructureUser = null;
            for (String userId : userIds) {
                baseStructureUser = new BaseStructureUser();
                baseStructureUser.setStructureId(userStructureAddVO.getStructureId());
                baseStructureUser.setUserId(userId);
                baseStructureUser.setId(UUID.randomUUID().toString());
                baseStructureUser.setCreateBy(user.getId());
                baseStructureUsers.add(baseStructureUser);
            }
            return baseStructureUserMapper.insertBatch(baseStructureUsers);
        }
        return 0;

    }

    @Override
    public List<StructureUserVO> selectUsersByStructureId(String structureId) {
        return baseStructureMapper.selectUsersByStructureId(structureId);
    }

    @Override
    public List<StructureUserVO> selectUsersByOrgId(String orgId) {
        return baseStructureMapper.selectUsersByOrgId(orgId);
    }

    @Override
    public int deleteUsersForStructure(StructureUserDeleteVO structureUserDeleteVO) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return baseStructureUserMapper.delete(structureUserDeleteVO.getUserIds(), user.getId(), structureUserDeleteVO.getStructureId());
    }

    @Override
    public int deleteUsersForOrg(OrgUserDeleteVO orgUserDeleteVO) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        return baseStructureUserMapper.deleteByOrgId(orgUserDeleteVO.getUserIds(), user.getId(), orgUserDeleteVO.getOrgId());
    }

    @Override
    public List<SysUser> selectUsersNotInStructure(String structureId, String userName) {
        return baseStructureMapper.selectUsersNotInStructure(structureId, userName);
    }

}

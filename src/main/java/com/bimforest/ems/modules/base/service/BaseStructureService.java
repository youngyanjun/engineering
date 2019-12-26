package com.bimforest.ems.modules.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.base.vo.*;
import com.bimforest.ems.modules.base.entity.BaseStructure;
import com.bimforest.ems.modules.sys.entity.SysUser;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-13
 */
public interface BaseStructureService extends IService<BaseStructure> {

    List<StructureTreeVO> selectStructureTree(String orgId);

    int addStructure(StructureAddVO structureAddVO);

    int updateStructure(StructureAddVO structureAddVO);

    Integer deleteStructure(String id);

    int addUsersForStructure(UserStructureAddVO userStructureAddVO);

    List<StructureUserVO> selectUsersByStructureId(String structureId);

    int deleteUsersForStructure(StructureUserDeleteVO structureUserDeleteVO);

    List<StructureUserVO> selectUsersByOrgId(String orgId);

    int deleteUsersForOrg(OrgUserDeleteVO orgUserDeleteVO);

    List<SysUser> selectUsersNotInStructure(String structureId,String userName);

}

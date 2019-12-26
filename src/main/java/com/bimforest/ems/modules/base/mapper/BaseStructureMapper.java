package com.bimforest.ems.modules.base.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.base.vo.StructureTreeVO;
import com.bimforest.ems.modules.base.vo.StructureUserVO;
import com.bimforest.ems.modules.base.entity.BaseStructure;
import com.bimforest.ems.modules.sys.entity.SysUser;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-13
 */
public interface BaseStructureMapper extends BaseMapper<BaseStructure> {

    List<StructureTreeVO> queryStructureSelectVOs(@Param("orgId") String orgId);

    Integer deleteStructure(@Param("id") String id);

    List<StructureUserVO> selectUsersByStructureId(String structureId);

    List<StructureUserVO> selectUsersByOrgId(String orgId);

    List<SysUser> selectUsersNotInStructure(String structureId, String userName);
}

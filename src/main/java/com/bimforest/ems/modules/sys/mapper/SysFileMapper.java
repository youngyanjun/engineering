package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.sys.entity.SysFile;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-04
 */
public interface SysFileMapper extends BaseMapper<SysFile> {

    int insertBatch(List<SysFile> records);

    int updateStatusByStatus(@Param("bizId") String bizId, @Param("bizType") String bizType, @Param("fromStatus") Integer fromStatus, @Param("toStatus") Integer toStatus);
}

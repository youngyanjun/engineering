package com.bimforest.ems.modules.log.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.log.VO.LogVO;
import com.bimforest.ems.modules.log.entity.SysLog;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.lettuce.core.dynamic.annotation.Param;

/**
 * <p>
 * 系统操作日志 Mapper 接口
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
public interface SysLogMapper extends BaseMapper<SysLog> {

    IPage<LogVO> selectLogPage(Page page, @Param("startDate") String startDate, @Param("endDate") String endDate);
}

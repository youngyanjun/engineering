package com.bimforest.ems.modules.log.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.log.VO.LogVO;
import com.bimforest.ems.modules.log.entity.SysLog;

import java.util.Date;
import java.util.List;

/**
 * <p>
 * 系统操作日志 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
public interface SysLogService extends IService<SysLog> {

    //日志列表查询
    IPage<LogVO> selectLogList(String startDate, String endData, Page page);
}

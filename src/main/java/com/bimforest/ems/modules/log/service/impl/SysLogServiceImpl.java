package com.bimforest.ems.modules.log.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.modules.log.VO.LogVO;
import com.bimforest.ems.modules.log.entity.SysLog;
import com.bimforest.ems.modules.log.mapper.SysLogMapper;
import com.bimforest.ems.modules.log.service.SysLogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 系统操作日志 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
@Service
public class SysLogServiceImpl extends ServiceImpl<SysLogMapper, SysLog> implements SysLogService {
    @Autowired
    SysLogMapper sysLogMapper;
    @Override
    public IPage<LogVO> selectLogList(String startDate, String endData, Page page) {
        return sysLogMapper.selectLogPage(page,startDate,endData);
    }
}


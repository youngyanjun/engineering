package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.SysFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.SysFileVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-04
 */
public interface SysFileService extends IService<SysFile> {

    int insertSysFiles(List<SysFileVO> sysFileVOS, String bizId, String bizType);

}

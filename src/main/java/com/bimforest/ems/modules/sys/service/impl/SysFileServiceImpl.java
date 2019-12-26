package com.bimforest.ems.modules.sys.service.impl;

import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.common.enums.SysFileTypeEnums;
import com.bimforest.ems.modules.sys.entity.SysFile;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.mapper.SysFileMapper;
import com.bimforest.ems.modules.sys.service.SysFileService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.SysFileVO;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-04
 */
@Service
public class SysFileServiceImpl extends ServiceImpl<SysFileMapper, SysFile> implements SysFileService {

    @Autowired
    SysFileMapper sysFileMapper;

    /**
     * @Description: 插入文件
    * @param sysFileVOS 文件列表
    * @param bizId 业务id
    * @param bizType 业务类型
    * @return void
    * @author max.zhang
    * @date 2019/12/4 16:34
    */
    @Override
    public int insertSysFiles(List<SysFileVO> sysFileVOS, String bizId, String bizType) {
        Date now = new Date();
        int record = 0;
        // 置原来的无效
        sysFileMapper.updateStatusByStatus(bizId, bizType, StatusEnums.STATUS_VALID.getCode(), StatusEnums.STATUS_INVALID.getCode());
        // 插入文件
        if (!CollectionUtils.isEmpty(sysFileVOS)) {
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            List<SysFile> sysFiles = new LinkedList<>();
            SysFile sysFile = null;
            for (SysFileVO sysFileVO : sysFileVOS) {
                sysFile = new SysFile();
                BeanUtils.copyProperties(sysFileVO,sysFile);
                sysFile.setId(UUID.randomUUID().toString());
                sysFile.setCreateBy(user.getId());
                sysFile.setBizId(bizId);
                sysFile.setBizType(bizType);
                sysFiles.add(sysFile);
            }
            record = sysFileMapper.insertBatch(sysFiles);
        }
        return record;
    }

}

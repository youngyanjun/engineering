package com.bimforest.ems.modules.sys.service.impl;

import com.bimforest.ems.modules.sys.entity.SysMessage;
import com.bimforest.ems.modules.sys.mapper.SysMessageMapper;
import com.bimforest.ems.modules.sys.service.SysMessageService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.modules.sys.vo.SysMessageSelectVO;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-17
 */
@Service
public class SysMessageServiceImpl extends ServiceImpl<SysMessageMapper, SysMessage> implements SysMessageService {


    @Override
    public List<SysMessageSelectVO> selectSysMessagesByUserId(String userId) {
        return baseMapper.selectSysMessagesByUserId(userId);
    }

    @Override
    public int updateReadFlag(List<String> messageIds) {
        return baseMapper.updateReadFlag(messageIds);
    }

    @Override
    public SysMessageSelectVO getSysMessageById(String id) {
        return baseMapper.getSysMessageById(id);
    }

    @Override
    public int delete(List<String> messageIds, String updateUser) {
        return baseMapper.delete(messageIds, updateUser);
    }

    @Override
    public int selectUnReadMessageAmountForLoginUser(String userId) {
        return baseMapper.selectUnReadMessageAmountForLoginUser(userId);
    }
}

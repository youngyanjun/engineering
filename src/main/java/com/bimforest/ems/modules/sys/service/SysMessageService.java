package com.bimforest.ems.modules.sys.service;

import com.bimforest.ems.modules.sys.entity.SysMessage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.SysMessageSelectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-17
 */
public interface SysMessageService extends IService<SysMessage> {

    List<SysMessageSelectVO> selectSysMessagesByUserId(String userId);

    int updateReadFlag(List<String> messageIds);

    SysMessageSelectVO getSysMessageById(String id);

    int delete(List<String> messageIds, String updateUser);

    int selectUnReadMessageAmountForLoginUser(String userId);

}

package com.bimforest.ems.modules.sys.mapper;

import com.bimforest.ems.modules.sys.entity.SysMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.bimforest.ems.modules.sys.vo.SysMessageSelectVO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-17
 */
public interface SysMessageMapper extends BaseMapper<SysMessage> {

    List<SysMessageSelectVO> selectSysMessagesByUserId(String userId);

    int updateReadFlag(@Param("messageIds") List<String> messageIds);

    SysMessageSelectVO getSysMessageById(String id);

    int delete(@Param("messageIds") List<String> messageIds, @Param("updateUser") String updateUser);

    int selectUnReadMessageAmountForLoginUser(@Param("userId") String userId);

}

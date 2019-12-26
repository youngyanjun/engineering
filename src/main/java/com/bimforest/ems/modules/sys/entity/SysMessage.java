package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-17
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_message")
public class SysMessage extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 消息内容
     */
    private String content;

    /**
     * 消息状态：已读或未读
     */
    private Boolean readFlag;

    /**
     * 业务id
     */
    private String bizId;

    /**
     * 业务类型
     */
    private String bizType;

    private String receiverId;

}

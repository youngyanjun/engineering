package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.entity.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 *
 * @author max.zhang
 * @since 2019-12-04
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_file")
public class SysFile extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 业务id
     */
    private String bizId;

    /**
     * 业务类型
     */
    private String bizType;

    /**
     * 文件名
     */
    private String fileName;

    /**
     * 真实的文件名
     */
    private String realName;

    /**
     * 备注
     */
    private String remark;


}

package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 自定义字段字典管理
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dictionary")
public class Dictionary extends BaseEntity {

    private static final long serialVersionUID = 1L;


    /**
     * 字段组名称
     */
    private String dictionaryName;
    /**
     * 关联子类
     */
    private String dictionaryChild;
}

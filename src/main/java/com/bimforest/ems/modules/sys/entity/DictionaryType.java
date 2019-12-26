package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 自定义字段，分类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-11
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("sys_dictionary_type")
public class DictionaryType extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;



    /**
     * 排序ID
     */
    private Integer sortId;

    /**
     * 关联组ID
     */
    private String sysDictionaryId;

    /**
     * 字段内容
     */
    private String dictionaryType;


}

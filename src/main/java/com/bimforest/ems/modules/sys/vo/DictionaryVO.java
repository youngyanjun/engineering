package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @title: ${}
 * @date 2019-11-11 14:33
 */
@Data
public class DictionaryVO {
    private String id;
    private String name;
    /**
     * 内容
     */
    private String dictionaryChild;
}

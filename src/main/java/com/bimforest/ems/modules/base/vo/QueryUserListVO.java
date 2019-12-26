package com.bimforest.ems.modules.base.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-12-10 15:21
 */
@Data
public class QueryUserListVO {
    /**
     * 用户ID
     */
    private String id;
    /**
     * 用户姓名
     */
    private String userName;
    /**
     * 用户电话
     */
    private String phone;
}

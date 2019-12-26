package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

/**
 * @author max.zhang
 * @title: UserUpdateVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/1815:40
 * @Copyright (C) 量树科技
 */
@Data
public class UserUpdateVO {

    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 用户密码
     */
    private String newUserPwd;

    /**
     * 邮箱
     */
    private String eMail;




}

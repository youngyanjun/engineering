package com.bimforest.ems.common.constant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author max.zhang
 * @title: CommonConstant
 * @projectName bimforest
 * @description: TODO
 * @date 2019/9/1814:28
 * @Copyright (C) 量树科技
 */
public class CommonConstant {

    // session过期时间
    public static final long SESSION_EXPIRE_TIME = 60 * 60;

    // 为找回密码发送的短信验证码存放在Redis中，key的前缀
    public static final String UPDATE_PWD_VERIFICATION_CODE_REDIS_PREFIX = "update_pwd_verification_code_redis_prefix";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    // 采用的加密方式
    public static final String ENCRYPTION_ALGORITHM = "MD5";
    // 密码加密次数
    public static final int ENCRYPTION_NUMBER = 2;
    // 默认密码
    public static final String DEFAULT_PASSWORD = "123";
    // 文件访问公共地址
    public static final String FILE_URL = "/pi/";
    //周报
    public static final String WEEK_REPORT = "0";
    //月报
    public static final String MONTH_REPORT = "1";
    //形象进度表
    public static final String PROGRESS = "2";
    //普通用户
    public static final Integer USER_TYPE_ORDINARY = 1;
    //超管
    public static final Integer USER_TYPE_ADMIN = 0;
    //
    public static final String DEPARTMENT = "8";



}

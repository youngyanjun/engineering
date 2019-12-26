package com.bimforest.ems.common.constant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * 　* @description: 读取配置文件参数
 * 　* @author max.zhang
 * 　* @date 2019/9/10 13:21
 * 　* @Copyright (C) 量树科技
 */
@Configuration
public class ConfigConstant {


    /**
     * 　* @description: 密码盐值
     * 　* @author max.zhang
     * 　* @date 2019/9/10 13:33
     * 　* @Copyright (C) 量树科技
     */
    private static String passwordSlat;

    /**
     * 　* @description: jwt安全
     * 　* @author max.zhang
     * 　* @date 2019/9/10 13:33
     * 　* @Copyright (C) 量树科技
     */
    private static String tokenBase64Security;

    /**
     * 　* @description: session有效期
     * 　* @author max.zhang
     * 　* @date 2019/9/10 13:33
     * 　* @Copyright (C) 量树科技
     */
    private static int sessionExpireTime;

    /**
     * 　* @description: 系统域名
     * 　* @author max.zhang
     * 　* @date 2019/9/10 13:33
     * 　* @Copyright (C) 量树科技
     */
    private static String webDomain;


    @Value("${jwt.pwsalt}")
    public  void setPasswordSlat(String passwordSlat) {
        passwordSlat = passwordSlat;
    }

    @Value("${jwt.expire}")
    public void setSessionExpireTime(int sessionExpireTimes) {
        sessionExpireTime = sessionExpireTimes;
    }

    @Value("${jwt.tokenBase64Security}")
    public void setTokenBase64Security(String tokenBase64Securitys) {
        tokenBase64Security = tokenBase64Securitys;
    }

    public static String getWebDomain() {
        return webDomain;
    }

    @Value("${web.domain}")
    public void setWebDomain(String webDomain) {
        ConfigConstant.webDomain = webDomain;
    }



    public static String getPasswordSlat() {
        return passwordSlat;
    }

    public static String getTokenBase64Security() {
        return tokenBase64Security;
    }

    public static int getSessionExpireTime() {
        return sessionExpireTime;
    }


}

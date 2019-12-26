package com.bimforest.ems.common.utils;

import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

/**
 * @author youngyanjun
 * @date 2019-11-25 16:48
 */
public class RandomNumUtils {
    public static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();
}

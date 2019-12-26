package com.bimforest.ems.common.utils;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

/**
 * @author youngyanjun
 * @date 2019-12-09 8:52
 */
@Slf4j
public class CheckObjectIsNullUtils {
    public static boolean objCheckIsNull(Object object) throws IllegalAccessException {
        if (object == null){
            return true;
        }
        Class clazz = object.getClass(); // 得到类对象
        Field fields[] = clazz.getDeclaredFields(); // 得到所有属性
        boolean flag = true; //定义返回结果，默认为true
        for (Field field : fields) {
            field.setAccessible(true);
            Object fieldValue = null;
            fieldValue = field.get(object); //得到属性值
            if (fieldValue == null || fieldValue.equals("")) {
                log.info("fieldValue:"+fieldValue);
                 flag = false;
                break;
            }
        }
        return flag;
    }
}

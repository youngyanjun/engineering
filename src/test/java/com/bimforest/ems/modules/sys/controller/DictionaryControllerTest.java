package com.bimforest.ems.modules.sys.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * @author youngyanjun
 * @title: ${}
 * @date 2019-11-15 14:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class DictionaryControllerTest {
    @Autowired
    DictionaryController dictionaryController;

    /**
     * 查看自定义字段
     */
    @Test
    public void selectDictionaryList() {
        //不存在数据情况
        dictionaryController.selectDictionaryList("张");
        //有匹配值
        dictionaryController.selectDictionaryList("问题");
        //默认请求情况
        dictionaryController.selectDictionaryList("");
    }

    /**
     * 修改自定义字段类---子类
     */
    @Test
    public void updateDictionaryChild() {
        //修改自定义类型
        dictionaryController.updateDictionaryChild("1","{[xiangmiann,test,]}","量树之云修改");
    }
}
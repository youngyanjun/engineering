package com.bimforest.ems.modules.sys.controller;

import com.bimforest.ems.modules.base.controller.BaseOrganizationController;
import com.bimforest.ems.modules.sys.entity.BaseOrganization;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author youngyanjun
 * @title: ${}
 * @date 2019-11-25 9:22
 */



@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseOrganizationControllerTest {
    @Autowired
    BaseOrganizationController controller;

    /**
     * 查询组织机构
     */
    @Test
    public void selectOrganizationList() {
        //查询空值
        controller.selectOrganizationList("");
        //查询不存在值
        controller.selectOrganizationList("组织机构测试");
        //查询存在值一条
        controller.selectOrganizationList("更新测试");
        //查询存在值多条
        controller.selectOrganizationList("string");
    }

    /**
     * 修改组织机构
     */
    @Test
    public void updateOrganizationList() {

    }
}
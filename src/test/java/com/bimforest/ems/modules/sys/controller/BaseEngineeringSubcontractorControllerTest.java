package com.bimforest.ems.modules.sys.controller;

import com.bimforest.ems.modules.base.controller.BaseEngineeringSubcontractorController;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @author youngyanjun
 * @title: ${}
 * @date 2019-11-14 14:23
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseEngineeringSubcontractorControllerTest {
    @Autowired
    BaseEngineeringSubcontractorController subcontractController;
    @Test
    public void selectSubcontractList() {

        subcontractController.selectSubcontractList("yangyanxuinISD");

    }

    @Test
    public void testSelectSubcontractList() {

    }
}
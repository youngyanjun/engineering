package com.bimforest.ems.modules.sys.service.impl;

import com.bimforest.ems.modules.sys.mapper.BaseEngineeringSubcontractorMapper;
import com.bimforest.ems.modules.sys.vo.EngineeringInvolveSubcontractVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @author youngyanjun
 * @date 2019-11-13 16:48
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class BaseSubcontractorServiceImplTest {
    @Autowired
    BaseEngineeringSubcontractorMapper baseEngineeringSubcontractorMapper;
    @Test
    public void insertlveSubcontract() {
        List<EngineeringInvolveSubcontractVO> list = new ArrayList<>();

        EngineeringInvolveSubcontractVO involveSubcontractVO = new EngineeringInvolveSubcontractVO();
        EngineeringInvolveSubcontractVO involveSubcontractVO1 = new EngineeringInvolveSubcontractVO();
        String uuid = UUID.randomUUID().toString();
        involveSubcontractVO.setEngineeringId("123");
        involveSubcontractVO.setSubcontractorId("3234dsdf");
        involveSubcontractVO.setSubcontractorPrice("77.3");

        involveSubcontractVO1.setEngineeringId("00101101010");
        involveSubcontractVO1.setSubcontractorId("323433x象牙上dsdf");
        involveSubcontractVO1.setSubcontractorPrice("17733.3");
//        involveSubcontractVO.setId(uuid);
        list.add(involveSubcontractVO);
        list.add(involveSubcontractVO1);
    }
}
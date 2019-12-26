package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.sys.entity.EngineeringSystemWeekly;
import com.bimforest.ems.modules.sys.service.EngineeringSystemWeeklyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
@RestController
@RequestMapping("/api/sys/engineering-system-weekly")
public class EngineeringSystemWeeklyController {
    @Autowired
    EngineeringSystemWeeklyService systemWeeklyService;
    @GetMapping("selectSystemWeekly")
    public CommonResponse selectSystemWeekly() {
        return new CommonResponse(ResponseTypeEnums.SUCCESS,systemWeeklyService.selectList());

    }
    @GetMapping("updateSystemWeekly")
    public CommonResponse updateSystemWeekly(String id,String strDate ) {
        EngineeringSystemWeekly systemWeekly = new EngineeringSystemWeekly();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = simpleDateFormat.parse(strDate);
        } catch (ParseException e) {
            return new CommonResponse(ResponseTypeEnums.FAILED);
        }
        systemWeekly.setId(id);
        systemWeekly.setStartDate(date);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,systemWeeklyService.updateById(systemWeekly));
    }

}

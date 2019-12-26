package com.bimforest.ems.modules.base.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.base.vo.EngineeringRanageSelectVO;
import com.bimforest.ems.modules.base.vo.EngineeringRanageUpdateVO;
import com.bimforest.ems.modules.base.entity.BaseEngineeringRanage;
import com.bimforest.ems.modules.base.service.BaseEngineeringRanageService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 项目范围
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-21
 */
@Api(description = "工程范围")
@RestController
@RequestMapping("/api/base/engineering/ranage")
public class BaseEngineeringRanageController {

    @Autowired
    BaseEngineeringRanageService baseEngineeringRanageService;

    @ApiOperation("查询工程范围")
    @GetMapping("/selectEngineeringRanages")
    public CommonResponse selectEngineeringRanages() {
        List<EngineeringRanageSelectVO> engineeringRanageSelectVOS = baseEngineeringRanageService.selectEngineeringRanages();
        return new CommonResponse(ResponseTypeEnums.SUCCESS, engineeringRanageSelectVOS);
    }

    @ApiOperation("更新工程范围")
    @PostMapping("/updateEngineeringRanage")
    public CommonResponse updateEngineeringRanage(@RequestBody EngineeringRanageUpdateVO engineeringRanageUpdateVO) {
        BaseEngineeringRanage baseEngineeringRanage = new BaseEngineeringRanage();
        BeanUtils.copyProperties(engineeringRanageUpdateVO, baseEngineeringRanage);
        boolean successFlag = baseEngineeringRanageService.updateById(baseEngineeringRanage);
        return new CommonResponse(ResponseTypeEnums.SUCCESS, successFlag);
    }

    @ApiOperation("项目范围查询")
    @GetMapping("/selectRangeList")
    public CommonResponse selectRangeList(String engineeringId){

        return new CommonResponse(ResponseTypeEnums.SUCCESS,null,null,baseEngineeringRanageService.selectList(engineeringId));
    }
}

package com.bimforest.ems.modules.base.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.modules.base.vo.EngineeringSingleAddVO;
import com.bimforest.ems.modules.base.vo.EngineeringSingleDelVO;
import com.bimforest.ems.modules.base.vo.EngineeringSingleUpdateVO;
import com.bimforest.ems.modules.base.entity.BaseEngineeringSingle;
import com.bimforest.ems.modules.base.service.BaseEngineeringSingleService;
import com.bimforest.ems.modules.sys.entity.SysUser;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-23
 */
@Api(description = "工程单体")
@RestController
@RequestMapping("/api/base/engineering/single")
public class BaseEngineeringSingleController {

    @Autowired
    BaseEngineeringSingleService baseEngineeringSingleService;

    @ApiOperation("新增单体")
    @PostMapping("/add")
    public CommonResponse add(@RequestBody EngineeringSingleAddVO engineeringSingleAddVO) {
        BaseEngineeringSingle baseEngineeringSingle = new BaseEngineeringSingle();
        BeanUtils.copyProperties(engineeringSingleAddVO, baseEngineeringSingle);
        BaseEngineeringSingle single = baseEngineeringSingleService.getOne(new QueryWrapper<BaseEngineeringSingle>()
                .eq("status", StatusEnums.STATUS_VALID.getCode())
                .eq("engineering_id", engineeringSingleAddVO.getEngineeringId())
                .last("order by single_order desc limit 1")

        );
        int order = 0;
        if (single != null) {
            order = single.getSingleOrder();
            order++;
        }
        baseEngineeringSingle.setSingleOrder(order);
        baseEngineeringSingleService.save(baseEngineeringSingle);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation("删除单体")
    @PostMapping("/del")
    public CommonResponse add(@RequestBody EngineeringSingleDelVO engineeringSingleDelVO) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        baseEngineeringSingleService.delete(engineeringSingleDelVO.getIds(), user.getId());
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation("更新单体")
    @PostMapping("/update")
    public CommonResponse update(@RequestBody EngineeringSingleUpdateVO engineeringSingleUpdateVO) {
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        BaseEngineeringSingle baseEngineeringSingle = new BaseEngineeringSingle();
        BeanUtils.copyProperties(engineeringSingleUpdateVO, baseEngineeringSingle);
        baseEngineeringSingle.setUpdateBy(user.getId());
        baseEngineeringSingle.setUpdateDate(new Date());
        baseEngineeringSingleService.updateById(baseEngineeringSingle);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

    @ApiOperation("单体列表")
    @GetMapping("/list")
    public CommonResponse list(String engineeringId) {
        List<BaseEngineeringSingle> baseEngineeringSingles = baseEngineeringSingleService.list(new QueryWrapper<BaseEngineeringSingle>()
                .eq("status", StatusEnums.STATUS_VALID.getCode())
                .eq("engineering_id", engineeringId)
                .last("order by single_order")
        );
        return new CommonResponse(ResponseTypeEnums.SUCCESS, baseEngineeringSingles);
    }

    @ApiOperation("单体排序")
    @PostMapping("/order")
    public CommonResponse order(@RequestBody EngineeringSingleDelVO engineeringSingleDelVO) {
        if (CollectionUtil.isNotEmpty(engineeringSingleDelVO.getIds())) {
            List<BaseEngineeringSingle> baseEngineeringSingles = new LinkedList<>();
            BaseEngineeringSingle baseEngineeringSingle = null;
            int i = 0;
            SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
            for (String id : engineeringSingleDelVO.getIds()) {
                baseEngineeringSingle = new BaseEngineeringSingle();
                baseEngineeringSingle.setId(id);
                baseEngineeringSingle.setUpdateDate(new Date());
                baseEngineeringSingle.setSingleOrder(i);
                baseEngineeringSingle.setUpdateBy(user.getId());
                i++;
                baseEngineeringSingles.add(baseEngineeringSingle);
            }

            baseEngineeringSingleService.saveOrUpdateBatch(baseEngineeringSingles);
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS, null);
    }

}

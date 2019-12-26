package com.bimforest.ems.modules.log.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.log.VO.LogVO;
import com.bimforest.ems.modules.log.service.SysLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import sun.java2d.pipe.SpanShapeRenderer;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * <p>
 * 系统操作日志 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-04
 */
@Api(description="日志系统-搜索日志")
@RestController
@RequestMapping("/api/sys/log/sys-log")
public class SysLogController {
    @Autowired
    SysLogService sysLogService;

    @ApiOperation(value = "查询系统日志")
    @GetMapping("/selectLogList")
    public CommonResponse<IPage<LogVO>> selectLogList(String startTime, String endTime, @RequestParam(required = false, defaultValue = "10")
    @ApiParam("每页显示条数") Integer pageSize, @RequestParam(required = false, defaultValue = "1") @ApiParam("页数") Integer pageNum) throws ParseException {
        Page page = new Page(pageNum, pageSize);//page对象设置页数，每页大小

        return new CommonResponse(ResponseTypeEnums.SUCCESS, sysLogService.selectLogList(startTime, endTime, page));//返回查询对象
    }



}

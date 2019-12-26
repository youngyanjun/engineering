package com.bimforest.ems.modules.construction.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.modules.construction.vo.IndexReportVO;
import com.bimforest.ems.modules.construction.service.IndexService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-12-04
 */
@Api(description = "项目部报表索引")
@RestController
@RequestMapping("/api/web/report/index/")
public class EngineeringReportListController {
    @Autowired
    IndexService indexService;
    @ApiOperation(value = "周报-形象进度-月报索引",notes = "报表类型  0 项目部 周报  1 项目部 月报 2 项目部形象进度")
    @GetMapping("getWeeklyReport")
    public CommonResponse getWeeklyReport(String organizationId,String type,String name, @RequestParam(required = false, defaultValue = "10") @ApiParam("每页显示条数") int pageSize,
                                          @RequestParam(required = false, defaultValue = "1") @ApiParam("当前页码") int pageNum) {
        Page page = new Page(pageNum, pageSize);
        IPage<IndexReportVO> indexReportVOIPage =  indexService.selectList(page,organizationId,type,name);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,indexReportVOIPage);
    }
}

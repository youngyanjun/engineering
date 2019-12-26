package com.bimforest.ems.modules.construction.controller;


import com.bimforest.ems.annotation.OperationLog;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.MessageEnums;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.common.enums.SysFileTypeEnums;
import com.bimforest.ems.common.utils.CheckObjectIsNullUtils;
import com.bimforest.ems.common.utils.UploadUtils;
import com.bimforest.ems.modules.construction.vo.*;
import com.bimforest.ems.modules.construction.service.WengineeringReportService;
import com.bimforest.ems.modules.sys.service.EngineeringSystemWeeklyService;
import com.bimforest.ems.modules.sys.service.SysFileService;
import com.bimforest.ems.modules.sys.vo.SysFileAddVO;
import com.bimforest.ems.modules.sys.vo.SysFileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.text.ParseException;
import java.util.LinkedList;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-28
 */
@Api(description = "工程周报编辑")
@RestController
@RequestMapping("/api/web/report/weeklyreport/insert/")
public class WengineeringReportEditController {

    @Autowired
    WengineeringReportService reportService;
    @Autowired
    EngineeringSystemWeeklyService weeklyService;
    @Autowired
    SysFileService sysFileService;
    @Value("${web.upload-path}")
    private String uploadPath;

    /**
     * 新建信息
     *
     * @param baseInfoVO
     */

    @OperationLog(action = "报表管理",module = "新建周报")
    @ApiOperation("新建编辑基本信息")
    @PostMapping("insertBaseInfo")
    public CommonResponse insertBaseInfo(@RequestBody WeeklyBaseInfoVO baseInfoVO) throws ParseException {
        baseInfoVO.setState(StatusEnums.TEMPORARY_STORAGE.getCode());
        String id = reportService.insertBaseInfo(baseInfoVO);
        return new CommonResponse(ResponseTypeEnums.SUCCESS,id);
    }
    @ApiOperation("暂存基本信息")
    @PostMapping("editBaseInfo")
    public CommonResponse editBaseInfo(@RequestBody WeeklyBaseInfoVO baseInfoVO) throws ParseException {
        baseInfoVO.setState(StatusEnums.TEMPORARY_STORAGE.getCode());
        reportService.insertBaseInfo(baseInfoVO);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }
    @OperationLog(action = "报表管理",module = "提交周报")
    @ApiOperation("提交报表")
    @PostMapping("submitReport")
    public CommonResponse submitReport(@RequestBody  SubmitReport submitReport) throws IllegalAccessException {
        MustInfoVO mustInfoVO = new MustInfoVO();
        WeeklyMustFiledVO filedVO = reportService.selectMustInfo(submitReport.getWeeklyId());
        boolean b = CheckObjectIsNullUtils.objCheckIsNull(filedVO);
        if (!b){
            mustInfoVO.setBaseInfo(true);
            return new CommonResponse(ResponseTypeEnums.FAILED,MessageEnums.FIAL_SYS_2018.getCode(),MessageEnums.FIAL_SYS_2018.getMessage(), mustInfoVO);
        }
        reportService.submitReport(submitReport);
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }


    @ApiOperation(value = "新建编辑项目进展情况",notes = " 数组 machine/personnel  weeklyType键 :0 标识为本周施工人员或机械信息 1 标识为 下周人员或机械信息, weeklyId 为关联周报ID")
    @PostMapping("insertStageInfo")
    public CommonResponse insertStageInfo(@RequestBody StageInfoVO stageInfoVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.insertStageInfo(stageInfoVO));
    }

    @ApiOperation("上传形象进度图片")
    @PostMapping("uploadImageStage")
    public CommonResponse uploadImageStage(@RequestParam(value = "file") MultipartFile[] multipartFiles) throws Exception {
        List<SysFileVO> sysFileVOS = new LinkedList<>();
        if (multipartFiles != null && multipartFiles.length != 0) {
            for (MultipartFile multipartFile : multipartFiles) {
                SysFileVO sysFileVO = UploadUtils.upload(multipartFile, uploadPath);
                sysFileVOS.add(sysFileVO);
            }
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS, sysFileVOS);
    }

    @ApiOperation("新增|更新形象进度图片数据")
    @PostMapping("insertImageStage")
    public CommonResponse insertImageStages(@RequestBody SysFileAddVO sysFileAddVO) {
        int record = sysFileService.insertSysFiles(sysFileAddVO.getSysFileVOS(), sysFileAddVO.getBizId(), SysFileTypeEnums.IMAGE_PROGRESS.getCode());
        return new CommonResponse(ResponseTypeEnums.SUCCESS, record);
    }

    @ApiOperation("新建编辑下周计划及安排")
    @PostMapping("insertNextPlan")
    public CommonResponse insertNextPlan(@RequestBody StageInfoVO stageInfoVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.insertNextPlan(stageInfoVO));
    }

    @ApiOperation("新建编辑现场整改纪要")
    @PostMapping("insertSiteRectify")
    public CommonResponse insertSiteRectify(@RequestBody SiteRectifyVO siteRectifyVO) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.insertSiteRectify(siteRectifyVO));
    }

    @ApiOperation("新建编辑安全生产情况")
    @PostMapping("insertSafeInfo")
    public CommonResponse insertSafeInfo(@RequestBody SafeInfoVO safeInfoVO) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.insertSafeInfo(safeInfoVO));
    }

    @ApiOperation("新建编辑本周收发资料汇总")
    @PostMapping("insertInOutFile")
    public CommonResponse insertInOutFile(@RequestBody InOutFileVO baseInfoVO) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.insertInOutFile(baseInfoVO));
    }

    @ApiOperation("新建编辑可能影响工程的因素")
    @PostMapping("insertEffect")
    public CommonResponse insertEffect(@RequestBody EffectVO effectVO) {

        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.insertEffect(effectVO));
    }

    @ApiOperation("新建编辑现场其他需要说明")
    @PostMapping("insertSiteOtherInfo")
    public CommonResponse insertSiteOtherInfo(@RequestBody SiteOtherInfoVO otherInfoVO) {
        return new CommonResponse(ResponseTypeEnums.SUCCESS, reportService.insertSiteOtherInfo(otherInfoVO));
    }


}

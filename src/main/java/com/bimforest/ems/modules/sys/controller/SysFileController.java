package com.bimforest.ems.modules.sys.controller;


import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.utils.UploadUtils;
import com.bimforest.ems.modules.sys.vo.SysFileVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.LinkedList;
import java.util.List;

/**
* @Description: 文件 图片
* @return
* @author max.zhang
* @date 2019/12/12 13:58
*/
@Api(description = "文件上传")
@RestController
@RequestMapping("/api/sys/file")
public class SysFileController {

    @Value("${web.upload-path}")
    private String uploadPath;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
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
}

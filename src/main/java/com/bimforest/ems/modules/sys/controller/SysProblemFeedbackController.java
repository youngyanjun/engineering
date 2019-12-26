package com.bimforest.ems.modules.sys.controller;


import cn.hutool.core.collection.CollectionUtil;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.SysFileTypeEnums;
import com.bimforest.ems.modules.sys.entity.SysProblemFeedback;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.service.SysFileService;
import com.bimforest.ems.modules.sys.service.SysProblemFeedbackService;
import com.bimforest.ems.modules.sys.vo.ProblemFeedbackSaveVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-17
 */
@Api(description = "问题反馈")
@RestController
@RequestMapping("/api/sys/problem-feedback")
public class SysProblemFeedbackController {

    @Autowired
    SysProblemFeedbackService sysProblemFeedbackService;
    @Autowired
    SysFileService sysFileService;

    @ApiOperation(value = "保存问题反馈")
    @PostMapping("/saveProblemFeedback")
    public CommonResponse saveProblemFeedback(@RequestBody ProblemFeedbackSaveVO problemFeedbackSaveVO) {
        SysProblemFeedback sysProblemFeedback = new SysProblemFeedback();
        String id = UUID.randomUUID().toString();
        sysProblemFeedback.setId(id);
        SysUser user = (SysUser) SecurityUtils.getSubject().getPrincipal();
        sysProblemFeedback.setCreateBy(user.getId());
        BeanUtils.copyProperties(problemFeedbackSaveVO, sysProblemFeedback);
        sysProblemFeedbackService.save(sysProblemFeedback);
        if (CollectionUtil.isNotEmpty(problemFeedbackSaveVO.getSysFileVOS())) {
            sysFileService.insertSysFiles(problemFeedbackSaveVO.getSysFileVOS(), id, SysFileTypeEnums.PROBLEM_FEEDBACK.getCode());
        }
        return new CommonResponse(ResponseTypeEnums.SUCCESS);
    }

}

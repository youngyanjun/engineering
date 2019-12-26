package com.bimforest.ems.modules.sys.vo;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author max.zhang
 * @title: ProblemFeedbackSaveVO
 * @projectName bimforest-ems
 * @description: TODO
 * @date 2019/12/179:34
 * @Copyright (C) 量树科技
 */
@Data
public class ProblemFeedbackSaveVO {


    /**
     * 反馈内容
     */
    private String content;

    /**
     * 联系方式
     */
    private String contactInformation;

    private List<SysFileVO> sysFileVOS;
}

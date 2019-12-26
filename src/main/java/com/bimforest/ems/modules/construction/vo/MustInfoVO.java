package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-12-06 16:56
 */
@Data
public class MustInfoVO {
    /**
     * 基本信息
     */
    private boolean baseInfo;
    /**
     * 项目进展
     */
    private boolean stageInfo;
    /**
     * 形象进度
     */
    private boolean imageInfo;
    /**
     * 下周计划安排
     */
    private boolean nextStageInfo;
    /**
     * 现场整改纪要
     */
    private boolean siteDebugInfo;
    /**
     * 安全生产情况
     */
    private boolean safeInfo;
    /**
     * 本周收发资料
     */
    private boolean inOutFileInfo;
    /**
     * 可能影响工程的因素
     */
    private boolean effectInfo;
    /**
     * 其他需要说明
     */
    private boolean OtherInfo;




}

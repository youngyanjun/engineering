package com.bimforest.ems.modules.construction.vo;

import com.bimforest.ems.modules.construction.entity.WengineeringReportMachine;
import com.bimforest.ems.modules.construction.entity.WengineeringReportPersonnel;
import lombok.Data;

import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-11-29 14:08
 */
@Data
public class StageInfoVO {
    /**
     * 周报ID
     */
    private String id;
    /**
     * 需要领导协调的事
     */
    private String coordination;

    /**
     * 图纸设计情况
     */
    private String drawings;

    /**
     * 商务合同
     */
    private String contract;

    /**
     * 土建施工分项
     */
    private String civilengineeringSub;

    /**
     * 装配施工分项
     */
    private String prefabricatedSub;

    /**
     * 水电安装施工分项
     */
    private String waterpowerSub;

    /**
     * 暖通施工分项
     */
    private String havcSub;

    /**
     * 装饰施工分项
     */
    private String decorationSub;

    /**
     * 其他附属工程分项
     */
    private String otherSub;

    /**
     * 施工人员
     */
    private List<WengineeringReportPersonnel> personnel;

    /**
     * 施工机械
     */
    private List<WengineeringReportMachine> machine;

    /**
     * 项目基本信息备注
     */
    private String stageInfoRemark;



}

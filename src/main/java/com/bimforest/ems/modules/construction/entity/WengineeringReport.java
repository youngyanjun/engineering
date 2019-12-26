package com.bimforest.ems.modules.construction.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-28
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@TableName("reportw_engineering")
public class WengineeringReport extends BaseEntity {

    private static final long serialVersionUID = 1L;
    /**
     * 关联项目ID
     */
    private String organizationId;

    /**
     * 备注
     */
    private String remark;


    /**
     * 暂存、提交
     */
    private Integer state;

    /**
     * 工程名称
     */
    private String constructionName;

    /**
     * 建设单位
     */
    private String constructionUnit;

    /**
     * 质监单位
     */
    private String qualityInspectionUnit;

    /**
     * 质监站
     */
    private String qualityInspectionStation;

    /**
     * 项目经理
     */
    private String projecLeader;

    /**
     * 技术负责人
     */
    private String technicalLeader;

    /**
     * 安全员
     */
    private String safeMan;

    /**
     * 合同总价
     */
    private BigDecimal contractPrice;

    /**
     * 已经回款、已收款
     */
    private BigDecimal moneyBack;
    /**
     * 周次
     */
    private Integer weeklyNum;

    /**
     * 施工开始日期
     */
    private Date constructionDateStart;

    /**
     * 完工率
     */
    private Integer completionRate;
    /**
     * 施工结束日期
     */
    private Date constructionDateFinis;

    /**
     * 报送者
     */
    private String reportBy;

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
    private String personnel;

    /**
     * 施工机械
     */
    private String machine;

    /**
     * 需要领导协调的事---下周
     */
    private String nextCoordination;

    /**
     * 图纸设计情况---下周
     */
    private String nextDrawings;

    /**
     * 商务合同---下周
     */
    private String nextContract;

    /**
     * 土建施工分项---下周
     */
    private String nextCivilengineeringSub;

    /**
     * 装配施工分项---下周
     */
    private String nextPrefabricatedSub;

    /**
     * 水电安装施工分项---下周
     */
    private String nextWaterpowerSub;

    /**
     * 暖通施工分项---下周
     */
    private String nextHavcSub;

    /**
     * 装饰施工分项---下周
     */
    private String nextDecorationSub;

    /**
     * 其他附属工程分项---下周
     */
    private String nextOtherSub;

    /**
     * 施工人员---下周
     */
    private String nextPersonnel;

    /**
     * 施工机械---下周
     */
    private String nextMachine;

    /**
     * 现场整改纪要
     */
    private String rectification;

    /**
     * 本周安全生产情况
     */
    private String safeInfo;

    /**
     * 本周重大危险源
     */
    private String dangerSource;

    /**
     * 下周重大危险源
     */
    private String nextDangerSource;

    /**
     * 本周现场检查情况
     */
    private String siteCheck;

    /**
     * 公司内部联络
     */
    private String receiveFileIn;

    /**
     * 公司外部联络
     */
    private String receiveFileOut;

    /**
     * 可能影响的因素
     */
    private String effect;

    /**
     * 现场其他需要的说明
     */
    private String otherSiteInfo;

    /**
     * 本周天气情况
     */
    private String weatherCurrent;

    /**
     * 下周天气情况
     */
    private String weatherNext;

    /**
     * 项目基本信息备注
     */
    private String stageInfoRemark;

    /**
     * 下周项目基本信息备注
     */
    private String nextStageInfoRemark;

    /**
     * 报表类型
     */
    private String reportType;
    /**
     * 报表名称
     */
    private String reportName;

}

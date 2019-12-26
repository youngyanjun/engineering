package com.bimforest.ems.modules.sys.vo;

import com.bimforest.ems.modules.base.entity.BaseEngineeringRanage;
import com.bimforest.ems.modules.base.entity.BaseEngineeringSubcontractor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-11-13 9:26
 */
@Data
public class EngineeringAddVO implements Serializable {
    private String id;
    private String engineeringCode;
    private String engineeringName;
    private String engineeringAbbreviation;
    private String contentPrice;
    private String managerId;
    private String orgId;
    private List<BaseEngineeringRanage>  rangeList;
    private List<BaseEngineeringSubcontractor> subcontractList;
    private String flag;
}

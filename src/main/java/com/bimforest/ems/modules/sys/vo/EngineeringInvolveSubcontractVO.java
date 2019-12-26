package com.bimforest.ems.modules.sys.vo;

import com.aliyuncs.RpcAcsRequest;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.Data;

import java.io.Serializable;

/**
 * @author youngyanjun
 * @title: ${}
 * @date 2019-11-13 10:35
 */
@Data
public class EngineeringInvolveSubcontractVO implements Serializable {
    private String id;
    private String subcontractorId;
    private String subcontractorPrice;
    private String engineeringId;
    private String subcontractName;

}

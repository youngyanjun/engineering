package com.bimforest.ems.modules.construction.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author youngyanjun
 * @date 2019-12-04 12:51
 */
@Data
public class IndexReportVO {
private String id;
private String reportName;
private String month;
private String type;
private Date updateAt;
private String state;
}

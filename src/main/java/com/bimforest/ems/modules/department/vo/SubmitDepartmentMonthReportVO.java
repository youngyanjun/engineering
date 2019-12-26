package com.bimforest.ems.modules.department.vo;

import lombok.Data;

import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-12-16 11:07
 */
@Data
public class SubmitDepartmentMonthReportVO {
    //报表ID
    private String reportId;
    //发送信息内容
    private List<String> smList;


}

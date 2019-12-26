package com.bimforest.ems.modules.department.vo;

import lombok.Data;

import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-12-20 11:16
 */
@Data
public class GetOrgEngUserListVO {

    private String engId;
    private List<GetOrgEngUserVO> list;
}

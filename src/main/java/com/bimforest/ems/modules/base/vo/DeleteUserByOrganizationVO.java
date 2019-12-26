package com.bimforest.ems.modules.base.vo;

import lombok.Data;

import java.util.List;

/**
 * @author youngyanjun
 * @date 2019-12-10 17:00
 */
@Data
public class DeleteUserByOrganizationVO {
    private String organizationId;
    private List<String> userId;

}

package com.bimforest.ems.modules.sys.vo;

import com.bimforest.ems.pojo.BaseTreeNode;
import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-11-15 16:42
 */
@Data
public class RoleListVO extends BaseTreeNode  {
    private String roleName;
    private String remark;
}

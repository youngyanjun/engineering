package com.bimforest.ems.modules.sys.vo;

import com.bimforest.ems.pojo.BaseTreeNode;
import lombok.Data;

/**
 * @author youngyanjun
 * @date 2019-11-18 8:59
 */
@Data
public class MenuListVO extends BaseTreeNode  {
    /**
     * 备注
     */
    private String remark;

    /**
     * 菜单名称
     */
    private String menuName;
    /**
     * 是否具有改权限---权限是否选择
     */
    private String selection;
    /**
     * 相关页面
     */
    private String relatePages;
}

package com.bimforest.ems.modules.sys.vo;

import lombok.Data;
import org.apache.shiro.subject.PrincipalCollection;

import java.time.LocalDate;

/**
 * @author youngyanjun
 * @date 2019-11-20 9:03
 */

@Data
public class SystemWeeklyVO {
    /**
     * 主键ID
     */
    private String id;
    /**
     * 项目ID
     */
    private String engineeringId;

    /**
     * 开始时间
     */
    private String startDate;

}

package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.bimforest.ems.pojo.BaseEntity;
import com.sun.xml.bind.v2.model.core.ID;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author youngyanjun
 * @since 2019-11-19
 */
@Data
@Accessors(chain = true)
@TableName("engineering_system_weekly")
public class EngineeringSystemWeekly extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * 项目ID
     */
    private String engineeringId;

    /**
     * 开始时间
     */
    private Date startDate;


}

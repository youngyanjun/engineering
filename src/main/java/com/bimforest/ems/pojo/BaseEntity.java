package com.bimforest.ems.pojo;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;

/**
 * 自动填充，实体类，需要自动填充的需要继承此实体
 * @author youngyanjun
 * @date 2019-11-06 13:12
 */
@Data
public class BaseEntity implements Serializable {
    @TableId(value = "id",type = IdType.UUID)
    protected String id;

    @TableField(value = "create_date",fill = FieldFill.INSERT)
    protected Date createDate;
    @TableField(value = "update_date", fill = FieldFill.INSERT)
    protected Date updateDate;
    protected String createBy;
    protected String updateBy;
    @TableField(value = "status" ,fill = FieldFill.INSERT )
    protected Integer status;


}

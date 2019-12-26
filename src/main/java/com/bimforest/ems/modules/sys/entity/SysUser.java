package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

/**
 * <p>
 * 用户管理表
 * </p>
 *
 * @author youngyanjun
 * @since 2019-10-30
 */
@Data
@Accessors(chain = true)
@TableName("sys_user")
public class SysUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @TableId(value = "id",type = IdType.UUID)
    protected String id;

    @TableField(value = "create_Date",fill = FieldFill.INSERT)
    protected Date createDate;
    @TableField(value = "update_Date",fill = FieldFill.UPDATE)
    protected Date updateDate;
    protected String createBy;
    protected String updateBy;
    @TableField(value = "status" ,fill = FieldFill.INSERT )
    protected Integer status;

    /**
     * 备注
     */
    private String remark;


    /**
     * 用户姓名
     */
    private String userName;

    /**
     * 用户密码
     */
    private String userPwd;

    /**
     * 邮箱
     */
    private String eMail;

    /**
     * 手机
     */
    private String phone;

    /**
     * 用户账号是否冻结
     */
    private String isFreeze;

    /**
     * 自动保存时间设置
     */
    private String saveTime;
    /**
     * 用户权限列表
     */
    private Set<SysRole> roles;
    /**
     * 用户盐值
     */
    private String salt;
    /**
     * 用户类型
     */
    private Integer userType;

}

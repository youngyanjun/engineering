package com.bimforest.ems.modules.sys.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 
 * </p>
 *
 * @author max.zhang
 * @since 2019-12-17
 */
@Data
@Accessors(chain = true)
@TableName("sys_problem_feedback")
public class SysProblemFeedback {

    private String id;

    private static final long serialVersionUID = 1L;

    /**
     * 反馈内容
     */
    private String content;

    /**
     * 联系方式
     */
    private String contactInformation;

    /**
     * 添加图片，对应sys_file的id
     */
    private String fileId;

    private Date createDate;

    private String createBy;


}

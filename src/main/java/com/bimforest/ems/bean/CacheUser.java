package com.bimforest.ems.bean;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author youngyanjun
 * @date 2019-11-22 13:23
 */
@Data
@Builder
public class CacheUser implements Serializable {

    private static final long serialVersionUID = 1L;

    private String email;

    private Integer state;

    private String userName;

    private String token;

    private Integer userType;
}


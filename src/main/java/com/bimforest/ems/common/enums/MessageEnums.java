package com.bimforest.ems.common.enums;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;

/**
 * 　* @description: TODO
 * 　* @author max.zhang
 * 　* @date 2019/7/3 10:42
 *
 */
public enum MessageEnums {

  /**
   * 请求处理成功[2000]
   */
  //FIAL_SYS_2000("SYS", "SYS2000", "邮箱或手机号已经存在"),

  FIAL_SYS_2001("SYS", "SYS2001", "邮箱或手机号已经存在"),

  FIAL_SYS_4000("SYS", "SYS4000", "操作失败"),

  FIAL_SYS_2002("SYS", "SYS2002", "该用户还没有登录"),
  FIAL_SYS_2003("SYS", "SYS2003", "用户名或密码不正确"),
  FIAL_SYS_2004("SYS", "SYS2004", "手机号格式不正确"),
  FIAL_SYS_2005("SYS", "SYS2005", "系统中不存在该用户对应的手机号"),
  FIAL_SYS_2006("SYS", "SYS2006", "找回密码的短信60S内只能发送一次"),
  FIAL_SYS_2007("SYS", "SYS2007", "发送短信失败"),
  FIAL_SYS_2008("SYS", "SYS2008", "验证码错误"),
  FIAL_SYS_2009("SYS", "SYS2009", "手机号或邮箱格式错误"),
  FIAL_SYS_2010("SYS","SYS2010","企业名称或纳税人识别号已存在"),
  FIAL_SYS_2011("SYS","SYS2011","时间格式错误"),
  FIAL_SYS_2012("SYS","SYS2012","邮箱已被占用"),
  FIAL_SYS_2013("SYS","SYS2013","您不具有次权限"),
  FIAL_SYS_2014("SYS","SYS2014","已存在的项目编码"),
  FIAL_SYS_2015("SYS","SYS2014","项目名称或编码为空"),
  FIAL_SYS_2016("SYS","SYS2016","原密码错误"),
  FIAL_SYS_2017("SYS","SYS2017","无法再次提交"),
  FIAL_SYS_2018("SYS","SYS2018","您有必填项未填写项,请完成后再次提交"),
  FIAL_SYS_2019("SYS","SYS2019","您暂时未管理任何项目"),




    /*FIAL_SYS_2001("SYS", "SYS2001", "您没有访问权限，请联系管理员同步用户数据"),
    FIAL_SYS_2003("SYS", "SYS2003", "旧密码不正确"),
    FIAL_SYS_2004("SYS", "SYS2004", "角色名不能重复"),*/

  FIAL_WEB_4001("WX", "WX3001", "缺少请求参数！"),
  FIAL_WEB_4002("WX", "WX3001", "参数解析失败"),
  FIAL_WEB_4003("WX", "WX3001", "方法参数无效:"),
  FIAL_WEB_4004("WX", "WX3001", "参数绑定失败"),
  FIAL_WEB_4005("WX", "WX3001", "参数验证失败"),
  FIAL_WEB_401("WX", "WX3001", "登录异常"),
  FIAL_WEB_403("WX", "WX3001", "用户无权限"),
  FIAL_WEB_405("WX", "WX3001", "不支持当前请求方法！"),
  FIAL_WEB_415("WX", "WX3001", "不支持当前媒体类型！"),
  FIAL_WEB_422("WX", "WX3001", "所上传文件大小超过最大限制，上传失败！"),
  FIAL_WEB_500("WX", "WX3001", "服务内部异常");


  private String code;
  private String errorType;
  private String message;

  public String getCode() {
    return code;
  }

  public String getMessage() {
    return message;
  }

  public String getErrorType() {
    return errorType;
  }

  MessageEnums(String aerrorType, String acode, String amessage) {
    this.errorType = aerrorType;
    this.code = acode;
    this.message = amessage;
  }

  public static MessageEnums getByCode(String code) {
    if (StringUtils.isEmpty(code)) {
      return null;
    }
    for (MessageEnums con : MessageEnums.values()) {
      if (con.getCode().equals(code)) {
        return con;
      }
    }
    return null;
  }

  public static MessageEnums getByErrorType(String errorType) {
    if (StringUtils.isEmpty(errorType)) {
      return null;
    }
    for (MessageEnums con : MessageEnums.values()) {
      if (con.getErrorType().equals(errorType)) {
        return con;
      }
    }
    return null;
  }
}

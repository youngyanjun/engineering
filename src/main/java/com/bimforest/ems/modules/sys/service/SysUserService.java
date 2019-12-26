package com.bimforest.ems.modules.sys.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.bimforest.ems.bean.CacheUser;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.bimforest.ems.modules.sys.vo.SysUserVO;
import com.bimforest.ems.modules.sys.vo.UserRoleMenuListVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-10-30
 */
public interface SysUserService extends IService<SysUser> {

    //根据用户名---用户登录凭证---手机---Email从数据库中查询该用户
    SysUser selectByUserAccount(String account);

    //查出用户列表
    IPage<SysUserVO> selectList(Page page, String userName);

    //判断邮箱和手机是否符合数据规则
    boolean checkPhoneEMail(String eMail, String phone);

    //判断数据库中是否已经存在相同手机或邮箱
    boolean queryPhoneEmail(String eMail, String phone);

    //根据ID修改 status 的值
    int updateStatus(List<String> list, int status);

    //根据用户ID修改用户密码
    int updatePassword(List<String> list, String password);

    //判断修改时用户输入的邮箱是否符合数据规则
    boolean checkEMail(String eMail);

    //修改时判断邮箱是否被占用
    int checkEmailIsEnable(String email, String id);
    //项目用户列表
    List selectUserList();

    //查询一个用户
    SysUser selectByUserName(String userName);
    //用户登录
    CacheUser login(String userName, String password);

    //用户登出
    void userLogout();
    //查询用户项目
    SysUser selectUserEngineering(String phone);
    //查询一条邮箱记录
    int selectOneEmail(String email, String id);
    //更新用户密码
    int updateUserPwd(String id, String oldPwd, String newPwd);
    //用户角色权限列表
     UserRoleMenuListVO selectUserRoleMenu(String id);
    //批量重置用户密码
    int updateBatchUserPwd(List<String> ids);

    boolean checkUserPassWord(String userId, String pwd);
}

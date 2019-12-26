package com.bimforest.ems.modules.sys.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.bimforest.ems.common.CommonResponse;
import com.bimforest.ems.common.constant.CommonConstant;
import com.bimforest.ems.common.enums.ResponseTypeEnums;
import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.bean.CacheUser;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.mapper.SysUserMapper;
import com.bimforest.ems.modules.sys.service.SysUserService;
import com.bimforest.ems.modules.sys.vo.SysUserVO;
import com.bimforest.ems.modules.sys.vo.UserRoleMenuListVO;
import com.bimforest.utils.StrUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author youngyanjun
 * @since 2019-10-30
 */
@Slf4j
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

    @Autowired
    SysUserMapper sysUserMapper;
    @Autowired
    SysUserService sysUserService;

    /**
     * 根据用户名---用户登录账号---手机---Email从数据库中查询该用户
     *
     * @param account
     * @return
     */
    @Override
    public SysUser selectByUserAccount(String account) {
        //查询条件构造器
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        //通过“phone” 并且“status” 行 不为 “-1” 的条件查出用户信息（“status” 为 “-1” 是逻辑删除标志）
        queryWrapper.eq("phone", account).ne("status", StatusEnums.ENABLED_N.getCode());
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 人员管理搜索，列表显示
     *
     * @param page
     * @param userName
     * @return
     */
    @Override
    public IPage<SysUserVO> selectList(Page page, String userName) {
        //模糊查询用户及查询用户全部列表
        return sysUserMapper.selectUserList(page, userName);
    }

    /**
     * 判断邮箱和手机是否符合数据规则
     *
     * @param eMail
     * @param phone
     * @return
     */
    @Override
    public boolean checkPhoneEMail(String eMail, String phone) {
        if (StringUtils.isEmpty(eMail) | StringUtils.isEmpty(phone)) {
            return false;
        }
        String eMailTemplate = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";
        String phoneTemplate = "[0-9-()（）]{7,18}";

        boolean isEMail = eMail.matches(eMailTemplate);
        boolean isPhone = phone.matches(phoneTemplate);

        if (isEMail && isPhone) {  //验证手机及邮箱都为真则返回 true
            return true;
        }
        return false;
    }

    /**
     * 判断邮箱是否符合数据规则
     *
     * @param eMail
     * @param eMail
     * @return
     */
    @Override
    public boolean checkEMail(String eMail) {
        if (StringUtils.isEmpty(eMail)) {
            return false;
        }
        String eMailTemplate = "\\w[-\\w.+]*@([A-Za-z0-9][-A-Za-z0-9]+\\.)+[A-Za-z]{2,14}";

        boolean isEMail = eMail.matches(eMailTemplate);


        return isEMail;
    }

    /**
     * 检查邮箱是非被占用
     *
     * @param email
     * @return
     */
    @Override
    public int checkEmailIsEnable(String email, String id) {
        return sysUserMapper.checkEmailIsEnable(email, id);
    }

    /**
     * 项目管理--用户列表查询
     *
     * @return
     */
    @Override
    public List selectUserList() {

        return sysUserMapper.queryUserList();
    }

    /**
     * 查询单挑信息
     *
     * @param userName
     * @return
     */
    @Override
    public SysUser selectByUserName(String userName) {
        return baseMapper.selectOne(
                new LambdaQueryWrapper<SysUser>().eq(SysUser::getPhone, userName)
        );
    }

    /**
     * 登录
     *
     * @param userName
     * @param password
     * @return
     */
    @Override
    public CacheUser login(String userName, String password) {

        // 获取Subject实例对象，用户实例
        Subject currentUser = SecurityUtils.getSubject();

        // 将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
        CacheUser cacheUser = null;

        // 4、认证
        try {
            // 传到 MyShiroRealm 类中的方法进行认证
            currentUser.login(token);
            // 构建缓存用户信息返回给前端
            SysUser user = (SysUser) currentUser.getPrincipals().getPrimaryPrincipal();
            cacheUser = CacheUser.builder()
                    .token(currentUser.getSession().getId().toString())
                    .build();
            BeanUtils.copyProperties(user, cacheUser);
            log.warn("CacheUser is {}", cacheUser.toString());
            return cacheUser;
        } catch (UnknownAccountException e) {
            log.error("账户不存在异常：", e);
        } catch (IncorrectCredentialsException e) {
            log.error("凭据错误（密码错误）异常：", e);
        } catch (AuthenticationException e) {
            log.error("身份验证异常:", e);
        }
        return null;
    }

    /**
     * 登出
     *
     * @return
     */
    @Override
    public void userLogout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.getPrincipal() == null) {
            return;
        }
        subject.logout();
    }

    /**
     * 查询单个用户信息
     *
     * @param phone
     * @return
     */
    @Override
    public SysUser selectUserEngineering(String phone) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("phone", phone);
        return sysUserMapper.selectOne(queryWrapper);
    }

    /**
     * 判断是否有相同的邮箱
     *
     * @param email
     * @return
     */
    @Override
    public int selectOneEmail(String email, String id) {
        return sysUserMapper.selectOneEmail(email, id);
    }

    /**
     * 用户修改密码
     *
     * @param oldPwd
     * @param newPwd
     * @return
     */
    @Override
    public int updateUserPwd(String id, String oldPwd, String newPwd) {
        //数据库中的原有密码
        SysUser sysUser = sysUserMapper.selectById(id);
        String userPwd = sysUser.getUserPwd();
        //获取盐值（使用的盐值是每个用户都具有不同的盐，不是共用一个盐值）
        String salt = sysUser.getSalt();
        //加密原密码
        String oldPassword =
                new SimpleHash(CommonConstant.ENCRYPTION_ALGORITHM, oldPwd,
                        ByteSource.Util.bytes(salt), CommonConstant.ENCRYPTION_NUMBER).toHex();

        if (!userPwd.equals(oldPassword)) {
            return -1;
        }
        //加密后的新密码
        String newPassword =
                new SimpleHash(CommonConstant.ENCRYPTION_ALGORITHM, newPwd,
                        ByteSource.Util.bytes(salt), CommonConstant.ENCRYPTION_NUMBER).toHex();
        //存储密码到数据库
        SysUser sysUserNew = new SysUser();
        sysUserNew.setUserPwd(newPassword);
        sysUserNew.setId(id);
        int i = sysUserMapper.updateById(sysUserNew);
        return i;
    }

    @Override
    public UserRoleMenuListVO selectUserRoleMenu(String id) {
        return null;
    }

    @Override
    public int updateBatchUserPwd(List<String> ids) {
        for (String id : ids) {
            //获取用户盐值
            SysUser sysUser = sysUserMapper.selectById(id);
            if (sysUser == null) {
                return -1;
            }
            String salt = sysUser.getSalt();
            //新密码
            String defaultPasswordP = CommonConstant.DEFAULT_PASSWORD;
            //加密新密码
            String newPassword =
                    new SimpleHash(CommonConstant.ENCRYPTION_ALGORITHM, defaultPasswordP,
                            ByteSource.Util.bytes(salt), CommonConstant.ENCRYPTION_NUMBER).toHex();
            SysUser user = new SysUser();
            user.setId(id);
            user.setUserPwd(newPassword);
            sysUserMapper.updateById(user);
        }
        return ids.size();
    }

    /**
     * 查询数据库中是否已经存在相同手机或邮箱
     *
     * @param eMail
     * @param phone
     * @return
     */
    @Override
    public boolean queryPhoneEmail(String eMail, String phone) {
        QueryWrapper<SysUser> queryWrapper = new QueryWrapper();
        queryWrapper.eq("e_mail", eMail).or().eq("phone", phone);
        Integer integer = sysUserMapper.selectCount(queryWrapper);
        if (integer > 0) {
            return true;
        }
        return false;
    }

    /**
     * 根据ID修改status中的状态值
     *
     * @param list
     * @return
     */
    @Override
    public int updateStatus(List<String> list, int status) {
        return sysUserMapper.updateStatus(list, status);
    }

    /**
     * 根据用户ID重置用户密码
     *
     * @param list
     * @param password
     * @return
     */
    @Override
    public int updatePassword(List list, String password) {
        return sysUserMapper.updatePassword(list, password);
    }


    @Override
    public boolean checkUserPassWord(String userId, String pwd) {
        // 获取用户
        SysUser sysUser = sysUserMapper.selectById(userId);
        //获取盐值（使用的盐值是每个用户都具有不同的盐，不是共用一个盐值）
        String salt = sysUser.getSalt();
        //加密待校验密码
        pwd = new SimpleHash(CommonConstant.ENCRYPTION_ALGORITHM, pwd,
                ByteSource.Util.bytes(salt), CommonConstant.ENCRYPTION_NUMBER).toHex();
        // 用户真实密码
        String userPwd = sysUser.getUserPwd();
        // 密码校验返回结果，默认为true
        boolean resultFlag = false;
        if (StrUtils.equals(userPwd, pwd)) {
            resultFlag = true;
        }
        return resultFlag;
    }
}

package com.bimforest.ems.config;

import com.bimforest.ems.common.enums.StatusEnums;
import com.bimforest.ems.modules.sys.entity.SysMenu;
import com.bimforest.ems.modules.sys.entity.SysRole;
import com.bimforest.ems.modules.sys.entity.SysUser;
import com.bimforest.ems.modules.sys.service.SysMenuService;
import com.bimforest.ems.modules.sys.service.SysRoleService;
import com.bimforest.ems.modules.sys.service.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

/**
 * @author youngyanjun
 * @date 2019-11-22 9:03
 */
@Slf4j
public class MyShiroRealm extends AuthorizingRealm {
    @Resource
    private SysUserService sysUserService;

    @Resource
    private SysRoleService roleService;

    @Resource
    SysMenuService menuService;

    /**
     * 授权----判断已经登录的用户具有什么样的权限
     *
     * @param principals
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.warn("开始执行授权操作.....");
        //授权操作做类
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        SysUser user = (SysUser) principals.getPrimaryPrincipal();
        //查询用户角色，一个用户可能有多个角色
        List<SysRole> roles = roleService.selectUserRoles(user.getId());
        //采用循环授权方式
        for (SysRole role : roles) {
            authorizationInfo.addRole(role.getRole());
            // 根据角色查询权限
            List<SysMenu> menus = menuService.selectRoleMenu(role.getId());
            for (SysMenu m : menus) {
                authorizationInfo.addStringPermission(m.getHref());
            }
        }
        return authorizationInfo;
    }

    /**
     * 主要是用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确。
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        token.getCredentials();

        //获取用户输入的账号
        String userName = (String) token.getPrincipal();
        //通过username从数据库中查找 User对象.
        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        SysUser user = sysUserService.selectByUserName(userName);
        if (Objects.isNull(user)) {
            return null;
        }
        //判断用户账号被冻结则返回null
        if (user.getStatus() != StatusEnums.ENABLED_Y.getCode()) {
            return null;
        }
        return new SimpleAuthenticationInfo(
                // 这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
                user,
                // 密码
                user.getUserPwd(),
                // salt
                ByteSource.Util.bytes(user.getSalt()),
                // realm name
                getName()
        );
    }
}

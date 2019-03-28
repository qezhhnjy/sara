package com.qezhhnjy.login.config.shiro;

import com.qezhhnjy.login.entity.Permission;
import com.qezhhnjy.login.entity.Role;
import com.qezhhnjy.login.entity.User;
import com.qezhhnjy.login.service.UserService;
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
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author fuzy
 * create time 19-3-25-上午10:48
 */
@Component
@Slf4j
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private UserService userService;

    /**
     * 权限信息，包括角色以及权限
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        log.info("权限配置");
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        User user = (User) principalCollection.getPrimaryPrincipal();
        for (Role role : user.getRoles()) {
            info.addRole(role.getRole());
            for (Permission permission : role.getPermissions()) {
                info.addStringPermission(permission.getPermission());
            }
        }
        return info;
    }

    /**
     * 主要用来进行身份认证的，也就是说验证用户输入的账号和密码是否正确
     *
     * @param token
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.info("身份认证 " + token);
        // 获取用户输入的账号
        String username = (String) token.getPrincipal();
        // 通过username从数据库中查找User对象
        // 实际项目中，这里可以根据实际情况做缓存，如果不做，shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
        User user = userService.findByUsername(username);
        if (user == null) return null;
        log.info(user.toString());
        //这里传入的是user对象，比对的是用户名，直接传入用户名也没错，但是在授权部分就需要自己重新从数据库里取权限
        return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getCredentialsSalt()), getName());
    }
}

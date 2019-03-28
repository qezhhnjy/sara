package com.qezhhnjy.login.service.impl;

import com.qezhhnjy.login.common.LoginResult;
import com.qezhhnjy.login.dao.UserRepository;
import com.qezhhnjy.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author fuzy
 * create time 19-3-26-下午10:03
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Override
    public LoginResult login(String username, String password) {
        return null;
    }

    @Override
    public void logout() {
        SecurityUtils.getSubject().logout();
    }
}

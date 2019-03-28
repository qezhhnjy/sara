package com.qezhhnjy.login.service.impl;

import com.qezhhnjy.login.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Service;

/**
 * @author fuzy
 * create time 19-3-26-下午10:03
 */
@Service
@Slf4j
public class LoginServiceImpl implements LoginService {

    @Override
    public void logout() {
        SecurityUtils.getSubject().logout();
    }
}

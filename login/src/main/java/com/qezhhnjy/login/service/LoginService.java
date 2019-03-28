package com.qezhhnjy.login.service;

import com.qezhhnjy.login.common.LoginResult;

/**
 * @author fuzy
 * create time 19-3-26-下午9:59
 */
public interface LoginService {

    LoginResult login(String username, String password);

    void logout();
}

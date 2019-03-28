package com.qezhhnjy.login.controller;

import com.baomidou.mybatisplus.core.toolkit.EncryptUtils;
import com.qezhhnjy.login.common.LoginResult;
import com.qezhhnjy.login.entity.User;
import com.qezhhnjy.login.service.LoginService;
import com.qezhhnjy.login.service.UserService;
import com.qezhhnjy.login.util.EncryptUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.annotation.Resource;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Map;

/**
 * @author fuzy
 * create time 19-3-26-下午10:25
 */
@Controller
@Slf4j
public class HomeController {

    @Resource
    private LoginService loginService;

    @Resource
    private UserService userService;

    @GetMapping({"/", "/index"})
    public String index() {
        return "index";
    }

    @GetMapping("/403")
    public String unauthorizedRole() {
        log.warn("-----没有权限-----");
        return "user/403";
    }

    @GetMapping("/logout")
    public String logout() {
        loginService.logout();
        return "login";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/sign_up")
    public String signUp() {
        return "user/sign_up";
    }

    @PostMapping("/sign_up")
    public String signUp(@Valid User user) {
        log.info(String.valueOf(user));
        EncryptUtil.encrypt(user);
        userService.save(user);
        return "login";
    }

    @PostMapping("/login")
    public String login(@Valid User user, Map<String, Object> map) {

        String username = user.getUsername();
        String password = user.getPassword();

        String msg;
        // 1. 获取Subject实例对象
        Subject currentUser = SecurityUtils.getSubject();

//        // 2、判断当前用户是否登录
//        if (currentUser.isAuthenticated() == false) {
//
//        }

        // 3.将用户名和密码封装到UsernamePasswordToken
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);

        // 4. 认证
        try {
            currentUser.login(token);
            Session session = currentUser.getSession();
            session.setAttribute("user", username);
            return "index";
        } catch (UnknownAccountException e) {
            msg = "账号不存在";
        } catch (IncorrectCredentialsException e) {
            msg = "密码不正确";
        } catch (AuthenticationException e) {
            msg = "用户验证失败";
        }
        log.warn(msg);
        map.put("error", msg);
        return "login";
    }

}

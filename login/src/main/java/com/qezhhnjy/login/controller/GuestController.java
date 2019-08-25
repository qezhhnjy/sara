package com.qezhhnjy.login.controller;

import com.qezhhnjy.login.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuzy
 * create time 19-3-25-下午4:12
 * 游客登录权限
 */
@RestController
@RequestMapping("/guest")
public class GuestController {

    @GetMapping("/enter")
    public Response login() {
        return Response.success().message("欢迎进入，您的身份是游客");
    }

    @GetMapping("/getMessage")
    public Response submitLogin() {
        return Response.success().message("您拥有获得该接口的信息的权限！");
    }
}

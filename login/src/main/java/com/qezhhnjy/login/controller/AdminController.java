package com.qezhhnjy.login.controller;

import com.qezhhnjy.login.common.Response;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author fuzy
 * create time 19-3-25-下午4:59
 * 管理员
 */
@RestController
@RequestMapping("/admin")
public class AdminController {

    @GetMapping("/getMessage")
    public Response getMessage() {
        return Response.success().message("您拥有管理员权限，可以获得该接口的信息！");
    }
}

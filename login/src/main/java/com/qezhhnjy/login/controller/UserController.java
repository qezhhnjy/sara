package com.qezhhnjy.login.controller;

import com.qezhhnjy.login.common.Response;
import com.qezhhnjy.login.entity.User;
import com.qezhhnjy.login.service.UserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import sun.misc.REException;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;


/**
 * @author fuzy
 * create time 19-3-25-下午4:56
 * 普通登录用户
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;

    @PostMapping("/info")
    // 权限管理
    @RequiresPermissions("user:view")
    public ResponseEntity info() {
        List<User> all = userService.findAll();
        return ResponseEntity.ok(Response.success(all));
    }

    @PostMapping("/add")
    @RequiresPermissions("user:add")
    public ResponseEntity add(@Valid User user) {
        User save = userService.save(user);
        return ResponseEntity.ok(Response.success(save));
    }

    @RequestMapping("/del")
    @RequiresPermissions("user:delete")
    public ResponseEntity delete(@Valid User user) {
        userService.delete(user);
        return ResponseEntity.ok(Response.success());
    }
}

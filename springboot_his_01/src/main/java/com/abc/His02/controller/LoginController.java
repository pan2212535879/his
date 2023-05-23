package com.abc.His02.controller;

import com.abc.His02.entity.User;
import com.abc.His02.service.UserService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

    @Autowired
    private UserService service;

    // http://localhost:8866/login?UserName=root&Password=root
    @RequestMapping("/login")
    public ResultUtil login(User u) {
        System.out.println("收到前台的数据："+ u);
        System.out.println("执行了login");
//        return service.login(u);
        User loginUser = service.login(u);
        System.out.println("数据库读取的User：" + loginUser);
        if (loginUser != null) {
            System.out.println("执行了if");
            return new ResultUtil(EnumCode.OK.getValue(), "登录成功", service.login(u));
        }
        return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "登录失败");
    }

    @RequestMapping("/logout")
    public ResultUtil logout() {
        return new ResultUtil(EnumCode.OK.getValue(), "登出成功");
    }
}

package com.abc.His02.controller;

import com.abc.His02.entity.Department;
import com.abc.His02.entity.User;
import com.abc.His02.service.UserService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService service;

    @RequestMapping("/queryUserAll")
    public ResultUtil getUserAll(User u) {
        List<User> userList = service.selectAll(u);
        if (userList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "查询成功", userList);
        }
        return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "查询失败");
    }

    @RequestMapping("/queryUserDeptByID")
    public ResultUtil getUserDeptByID(User u) {
        Department department = service.selectDeptByID(u);
        if (department != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "查询成功", department);
        }
        return new ResultUtil(EnumCode.BAD_REQUEST.getValue(), "查询失败");
    }
}

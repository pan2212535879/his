package com.abc.His02.controller;

import com.abc.His02.service.UserCenterService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @description 用户中心控制类
 */
@RestController
public class UserCenterController {

    @Autowired
    private UserCenterService service;

    /**
     * @return {@link ResultUtil}
     * @description 修改密码
     */
    @GetMapping("/userCenter/changePassword")
    public ResultUtil changePassword(@RequestParam Integer ID, @RequestParam String currentPass, @RequestParam String password, @RequestParam String checkPass) {
        System.out.println("传来的数据：" + ID + ", " + currentPass + ", " + password + ", " + checkPass);
        Integer code = EnumCode.OK.getValue();
        String msg = "修改密码成功";
        try {
            if (service.passwordIsRight(ID, currentPass) == null) {
                code = EnumCode.LOGIN_FAIL.getValue();
                msg = "原始密码错误";
            } else {
                service.changePassword(ID, password);
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "修改密码失败";
        }

        return new ResultUtil(code, msg);
    }
}

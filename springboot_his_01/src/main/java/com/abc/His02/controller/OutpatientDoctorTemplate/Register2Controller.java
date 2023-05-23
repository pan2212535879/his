package com.abc.His02.controller.OutpatientDoctorTemplate;


import com.abc.His02.service.OutpatientDoctorTemplate.IRegisterService;
import com.abc.His02.entity.Register;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 */
@RestController
public class Register2Controller {
    @Autowired
    IRegisterService service;

    @RequestMapping("/getRegisterAll")
    public ResultUtil getRegisterAll(Register register) {
        List<Register> registerList = service.selectAll(register);
        // System.out.println(register);
        if (registerList != null) {
            return new ResultUtil(EnumCode.OK.getValue(), "Success", registerList);
        }
        return new ResultUtil(EnumCode.GONE.getValue(), "查询不到");
    }

}

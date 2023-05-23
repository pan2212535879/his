package com.abc.His02.controller.Register;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import com.abc.His02.service.Register.RegisteredTollCollectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/registeredTollCollector/patientExpenseQuery")
public class RegisteredTollCollectorController {

    @Autowired
    private RegisteredTollCollectorService service;

    @RequestMapping("/selectAll")
    public ResultUtil selectAll(PatientCosts u){
        List<PatientCosts> list = service.selectAll(u);
        if(list != null){
            System.out.println("执行了查询语句");
            return  new ResultUtil(EnumCode.OK.getValue(),"查询成功",service.selectAll(u));
        }
        return new ResultUtil(EnumCode.UNAUTHORIZED.getValue(), "查询失败");
    }

}

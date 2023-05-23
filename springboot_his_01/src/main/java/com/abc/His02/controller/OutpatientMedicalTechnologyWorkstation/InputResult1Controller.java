package com.abc.His02.controller.OutpatientMedicalTechnologyWorkstation;

import com.abc.His02.entity.CheckApply;
import com.abc.His02.service.OutpatientMedicalTechnologyWorkstation.InputResult1Service;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/input")
public class InputResult1Controller {
    @Autowired
    private InputResult1Service service;

    @RequestMapping("/selectAllCheckApply")
    public ResultUtil selectAllCheckApply(CheckApply checkApply) {
        return new ResultUtil(EnumCode.OK.getValue(), "查询成功",service.selectAllCheckApply(checkApply));
    }

    @PostMapping("/updateCheckApply")
    public ResultUtil updateCheckApply(@RequestBody CheckApply checkApply) {
        System.out.println("修改执行");
        System.out.println(checkApply.getResult());
        return new ResultUtil(EnumCode.OK.getValue(), "修改成功",service.updateCheckApply(checkApply));
    }
}

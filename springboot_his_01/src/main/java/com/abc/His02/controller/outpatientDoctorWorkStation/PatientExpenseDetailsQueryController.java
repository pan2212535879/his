package com.abc.His02.controller.outpatientDoctorWorkStation;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.service.outpatientDoctorWorkStationService.PatientExpenseDetailsQueryService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @description 患者费用明细控制类
 */
@RestController
public class PatientExpenseDetailsQueryController {

    @Autowired
    private PatientExpenseDetailsQueryService service;

    /**
     * @return {@link ResultUtil}
     * @description 初始化待查患者费用明细的列表
     */
    @PostMapping("/outpatientDoctorWorkStation/patientExpenseDetailsQuery/initPatients")
    public ResultUtil initPatients(@RequestBody User user) {
        System.out.println("开始初始化待查询费用明细病人，传来的医生信息：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化待查询费用明细的患者数据成功";
        try {
            result = service.initPatients(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "初始化待查询费用明细的患者数据失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 根据姓名搜索患者
     */
    @RequestMapping("/outpatientDoctorWorkStation/patientExpenseDetailsQuery/searchByName")
    public ResultUtil searchByName(Register user) {
        System.out.println("搜索的姓名：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化待查询费用明细的患者数据成功";
        try {
            result = service.searchByName(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "搜索失败";
        }
        System.out.println("搜索的结果：" + result);
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 根据病历号搜索患者
     */
    @RequestMapping("/outpatientDoctorWorkStation/patientExpenseDetailsQuery/searchByNumber")
    public ResultUtil searchByNumber(Register user) {
        System.out.println("搜索的姓名：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "搜索成功";
        try {
            result = service.searchByNumber(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "搜索失败";
        }
        System.out.println("搜索的结果：" + result);
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 查询患者的费用明细
     */
    @PostMapping("/outpatientDoctorWorkStation/patientExpenseDetailsQuery/choosePatient")
    public ResultUtil choosePatient(@RequestBody Register register) {
        System.out.println("传来的患者挂号信息：" + register);
        List<PatientCosts> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化费用明细成功";
        try {
            result = service.choosePatient(register);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "初始化费用明细失败";
        }
        return new ResultUtil(code, msg, result);
    }

}

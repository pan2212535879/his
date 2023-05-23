package com.abc.His02.controller.outpatientDoctorWorkStation;

import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.service.outpatientDoctorWorkStationService.AfterDiagnosisService;
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
 * @description 诊毕控制类
 */
@RestController
public class AfterDiagnosisController {

    @Autowired
    private AfterDiagnosisService service;

    /**
     * @return {@link ResultUtil}
     * @description 初始化待诊毕的病人
     */
    @PostMapping("/outpatientDoctorWorkStation/afterDiagnosis/initPatients")
    public ResultUtil initPatients(@RequestBody User user) {
        System.out.println("开始初始化待诊毕病人，传来的医生信息：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化待诊毕病人数据成功";
        try {
            result = service.initPatients(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "初始化待诊毕病人数据失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 根据姓名搜索患者
     */
    @RequestMapping("/outpatientDoctorWorkStation/afterDiagnosis/searchByName")
    public ResultUtil searchByName(Register user) {
        System.out.println("搜索的姓名：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "搜索成功";
        try {
            result = service.searchByName(user);
            System.out.println("搜索的结果：" + result);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "搜索失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 根据病历号搜索患者
     */
    @RequestMapping("/outpatientDoctorWorkStation/afterDiagnosis/searchByNumber")
    public ResultUtil searchByNumber(Register user) {
        System.out.println("搜索的病历号：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "搜索成功";
        try {
            result = service.searchByNumber(user);
            System.out.println("搜索的结果：" + result);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "搜索失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化患者的病历首页
     */
    @RequestMapping("/outpatientDoctorWorkStation/afterDiagnosis/initRecord")
    public ResultUtil initRecord(MedicalRecord user) {
        System.out.println("初始化的病历患者：" + user);
        MedicalRecord result = new MedicalRecord();
        Integer code = EnumCode.OK.getValue();
        String msg = "已加载待诊毕病历首页";
        try {
            result = service.getRecord(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "加载待诊毕病历首页失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 对患者进行诊毕操作(更新挂号表)
     */
    @RequestMapping("/outpatientDoctorWorkStation/afterDiagnosis/commitEndPatient")
    public ResultUtil commitEndPatient(Register register) {
        System.out.println("即将诊毕的患者：" + register);
        Integer code = EnumCode.OK.getValue();
        String msg = "患者诊毕完成";
        try {
            service.commitEndPatient(register);
        } catch (Exception e) {
            code = EnumCode.INTERNAL_SERVER_ERROR.getValue();
            msg = "患者诊毕失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 对患者进行诊毕操作(更新病历首页表)
     */
    @RequestMapping("/outpatientDoctorWorkStation/afterDiagnosis/commitEndMedicalRecord")
    public ResultUtil commitEndMedicalRecord(MedicalRecord medicalRecord) {
        System.out.println("即将诊毕的病历：" + medicalRecord);
        Integer code = EnumCode.OK.getValue();
        String msg = "病历诊毕完成";
        try {
            service.commitEndMedicalRecord(medicalRecord);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "病历诊毕失败";
        }

        return new ResultUtil(code, msg);
    }
}

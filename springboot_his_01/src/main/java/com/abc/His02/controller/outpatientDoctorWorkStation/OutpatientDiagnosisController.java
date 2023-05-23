package com.abc.His02.controller.outpatientDoctorWorkStation;

import com.abc.His02.entity.*;
import com.abc.His02.entity.diagnosisTemplate.DiagnosisTemplate;
import com.abc.His02.service.outpatientDoctorWorkStationService.OutpatientDiagnosisServer;
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
 * @description 门诊确诊控制类
 */
@RestController
public class OutpatientDiagnosisController {
    @Autowired
    private OutpatientDiagnosisServer service;

    /**
     * @return {@link ResultUtil}
     * @description 初始化待确诊的患者信息
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/initPatients")
    public ResultUtil initPatients(@RequestBody User user) {
        System.out.println("开始初始化病人，传来的医生信息：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化病人数据成功";
        try {
            // 1.先初始化Register
            List<Register> temp = service.initPatients(user);
            for (int i=0; i<temp.size(); i++) {
                // 2.再从上面的数据里找到在MedicalRecord表里存在的，返回
                if (service.recordIsExist(temp.get(i)) != null) {
                    result.add(temp.get(i));
                }
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "初始化病人数据失败";
        }

        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 根据姓名搜索患者
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/searchByName")
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
    @RequestMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/searchByNumber")
    public ResultUtil searchByNumber(Register user) {
        System.out.println("搜索的姓名：" + user);
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
     * @description 初始化患者的门诊病历首页
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/initRecord")
    public ResultUtil initRecord(MedicalRecord user) {
        System.out.println("初始化的病历患者：" + user);
        MedicalRecord result = new MedicalRecord();
        Integer code = EnumCode.OK.getValue();
        String msg = "已加载门诊病历首页";
        try {
            result = service.getRecord(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "加载门诊病历首页失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化患者的检查结果
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/initCheckResult")
    public ResultUtil initCheckResult(MedicalRecord user) {
        System.out.println("初始化的患者检查：" + user);
        List<CheckApply> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "已加载患者所有检查结果";
        try {
            result = service.getCheckResult(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "加载患者所有检查结果失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化患者的处置结果
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/initDealResult")
    public ResultUtil initDealResult(MedicalRecord user) {
        System.out.println("初始化的患者检查：" + user);
        List<CheckApply> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "已加载患者所有检查结果";
        try {
            result = service.getDealResult(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "加载患者所有检查结果失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成诊断模板(个人)
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/getDiagnosisTemplatePersonally")
    public ResultUtil getRecordTemplatePersonally(@RequestBody User user) {
        System.out.println("user = " + user);
        List<DiagnosisTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成个人诊断模板成功";
        try {
            result = service.getDiagnosisTemplatePersonally(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成个人诊断模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成诊断模板(科室)
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/getDiagnosisTemplateByDepartment")
    public ResultUtil getRecordTemplateByDepartment(@RequestBody User user) {
        System.out.println("user = " + user);
        List<DiagnosisTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成科室诊断模板成功";
        try {
            result = service.getDiagnosisTemplateByDepartment(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成科室诊断模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成诊断模板(全院)
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/getDiagnosisTemplateByHospital")
    public ResultUtil getRecordTemplateByHospital() {
        List<DiagnosisTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成全院诊断模板成功";
        try {
            result = service.getDiagnosisTemplateByHospital();
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成全院诊断模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 删除诊断模板
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/deleteDiagnosisTemplate")
    public ResultUtil deleteRecordTemplate(@RequestBody DiagnosisTemplate diagnosisTemplate) {
        Integer code = EnumCode.OK.getValue();
        String msg = "删除成功";
        try {
            service.deleteDiagnosisTemplate(diagnosisTemplate);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "删除失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 添加诊断模板
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/commitTemplate")
    public ResultUtil commitTemplate(DiagnosisTemplate diagnosisTemplate) {
        System.out.println("即将添加的模板：" + diagnosisTemplate);
        Integer code = EnumCode.OK.getValue();
        String msg = "添加成功";
        try {
            service.commitTemplate(diagnosisTemplate);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "添加失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 确诊患者并更新病历信息
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/confirm")
    public ResultUtil confirm(MedicalRecord medicalRecord) {
        System.out.println("即将更新的病历信息：" + medicalRecord);
        Integer code = EnumCode.OK.getValue();
        String msg = "更新成功";
        try {
            service.confirm(medicalRecord);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "更新失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化疾病种类
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/diseaseCategory")
    public ResultUtil diseaseCategory() {
        List<DiseCategory> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "查询疾病种类成功";
        try {
            result = service.diseaseCategory();
            System.out.println("疾病种类：" + result);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "查询疾病种类失败";
        }

        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化疾病名称的前置函数
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/initDiseasePrepare")
    public ResultUtil initDiseasePrepare() {
        List<String> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成疾病成功";
        try {
            result = service.initDiseasePrepare();
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成疾病失败";
        }

        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化疾病种类的前置函数
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientDiagnosis/initCategoryDiseasePrepare")
    public ResultUtil initCategoryDiseasePrepare() {
        List<String> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成疾病种类成功";
        try {
            result = service.initCategoryDiseasePrepare();
            System.out.println("找到的疾病种类：" + result);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成疾病种类失败";
        }

        return new ResultUtil(code, msg, result);
    }
}

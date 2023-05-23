package com.abc.His02.controller.outpatientDoctorWorkStation;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.entity.recordTemplate.RecordTemplate;
import com.abc.His02.service.outpatientDoctorWorkStationService.OutpatientMedicalRecordHomePageService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @description 门诊病历首页控制类
 */
@RestController
public class OutpatientMedicalRecordHomePageController {

    @Autowired
    private OutpatientMedicalRecordHomePageService service;

    /**
     * @return {@link ResultUtil}
     * @description 初始化待诊患者列表
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/initPatients")
    public ResultUtil initPatients(@RequestBody User user) {
        System.out.println("开始初始化病人，传来的医生信息：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化病人数据成功";
        try {
            result = service.initPatients(user);
        } catch (Exception e) {
            code = EnumCode.INTERNAL_SERVER_ERROR.getValue();
            msg = "初始化病人数据失败";
        }

        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化已诊患者列表
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/initHistoryPatients")
    public ResultUtil initHistoryPatients(@RequestBody User user) {
        System.out.println("开始初始化诊毕病人，传来的医生信息：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化诊毕病人数据成功";
        try {
            result = service.initHistoryPatients(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "初始化诊毕病人数据失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 根据姓名搜索患者
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/searchByName")
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
    @RequestMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/searchByNumber")
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
     * @description 暂存门诊病历首页
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/saveRecord")
    public ResultUtil saveRecord(MedicalRecord user) {
        System.out.println("即将暂存的信息：" + user);
        Integer code = EnumCode.OK.getValue();
        String msg = "暂存成功";
        try {
            // 1.先在数据库里找是否有这个挂号记录
            if (service.recordIsExist(user) == null) {
                // 2.没有则插入新纪录
                service.insertRecord(user);
            } else {
                // 3.否则更新
                service.updateRecord(user);
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "暂存失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 提交门诊病历首页
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/commitRecord")
    public ResultUtil commitRecord(MedicalRecord user) {
        System.out.println("即将提交的信息：" + user);
        Integer code = EnumCode.OK.getValue();
        String msg = "提交成功";
        try {
            // 1.先在数据库里找是否有这个挂号记录
            if (service.recordIsExist(user) == null) {
                // 2.没有则插入新纪录
                System.out.println("此病人没有MedicalRecord记录，即将插入");
                service.insertRecord(user);
            } else {
                // 3.否则更新
                System.out.println("此病人有MedicalRecord记录，即将更新");
                service.updateRecord(user);
            }
            // 4.更新挂号表的字段
            service.updateRegister(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "提交失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化门诊病历首页
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/initRecord")
    public ResultUtil initRecord(MedicalRecord user) {
        System.out.println("初始化的病历患者：" + user);
        MedicalRecord result = new MedicalRecord();
        Integer code = EnumCode.OK.getValue();
        String msg = "已加载暂存病历首页";
        boolean flag = true;
        try {
            if (service.recordIsExist(user) == null) {
                flag = false;
                msg = "此患者没有暂存病历信息";
            } else {
                result = service.getRecord(user);
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "加载暂存病历首页失败";
        }
        if (flag) {
            return new ResultUtil(code, msg, result);
        } else {
            return new ResultUtil(code, msg);
        }
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成病历模板(个人)
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/getRecordTemplatePersonally")
    public ResultUtil getRecordTemplatePersonally(@RequestBody User user) {
        System.out.println("user = " + user);
        List<RecordTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成个人病历模板成功";
        try {
            result = service.getRecordTemplatePersonally(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成个人病历模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成病历模板(科室)
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/getRecordTemplateByDepartment")
    public ResultUtil getRecordTemplateByDepartment(@RequestBody User user) {
        System.out.println("user = " + user);
        List<RecordTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成科室病历模板成功";
        try {
            result = service.getRecordTemplateByDepartment(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成科室病历模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成病历模板(全院)
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/getRecordTemplateByHospital")
    public ResultUtil getRecordTemplateByHospital() {
        List<RecordTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成全院病历模板成功";
        try {
            result = service.getRecordTemplateByHospital();
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成全院病历模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 删除病历模板
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/deleteRecordTemplate")
    public ResultUtil deleteRecordTemplate(@RequestBody RecordTemplate record) {
        Integer code = EnumCode.OK.getValue();
        String msg = "删除成功";
        try {
            service.deleteRecordTemplate(record);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "删除失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 添加病历模板
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/commitTemplate")
    public ResultUtil commitTemplate(RecordTemplate record) {
        System.out.println("即将添加的模板：" + record);
        Integer code = EnumCode.OK.getValue();
        String msg = "添加成功";
        try {
            service.commitTemplate(record);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "添加失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 查询患者历史病历列表
     */
    @RequestMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/historyRegister")
    public ResultUtil historyRegister(Register register) {
        System.out.println("即将查询的患者历史病历：" + register);

        // 1.先根据传来的病历号在Register表中查身份证号
        System.out.println("搜索到的患者身份证号：" + service.searchIDNumber(register));
        // 2.根据身份证号，返回多项Register数据，因为一个人可以挂多次号
        System.out.println("根据身份证号查到的Register：" + service.searchRegister(service.searchIDNumber(register)));
        // 3.根据多个Register的CaseNumber，在MedicalRecord里查询
        // 3.1把每个案例号查询一次，赋值到List里
        List<Register> temp = new ArrayList<>();
        temp = service.searchRegister(service.searchIDNumber(register));
        class Temp{
            private String CaseNumber;
            @JsonFormat(pattern = "yyyy-MM-dd")
            private Date VisitDate;
            private String DeptName;
            private String Diagnosis;// 诊断结果

            public Temp(String caseNumber, Date visitDate, Integer deptID, String checkResult) {

            }

            public Temp() {

            }

            public String getCaseNumber() {
                return CaseNumber;
            }

            public void setCaseNumber(String caseNumber) {
                CaseNumber = caseNumber;
            }

            public Date getVisitDate() {
                return VisitDate;
            }

            public void setVisitDate(Date visitDate) {
                VisitDate = visitDate;
            }

            public String getDeptName() {
                return DeptName;
            }

            public void setDeptName(String deptName) {
                DeptName = deptName;
            }

            public String getDiagnosis() {
                return Diagnosis;
            }

            public void setDiagnosis(String diagnosis) {
                Diagnosis = diagnosis;
            }
        }
        List<Temp> result2 = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "查询成功";
        try {
            for (int i=0; i<temp.size(); i++) {
                System.out.println("第" + i + "temp:" + temp.get(i));
                System.out.println("第" + i + "次结果:" + service.searchHostory(temp.get(i)));
                // 3.2查询要返回给前端的数据 看诊日期、科室、诊断结果

                if (service.searchHostory(temp.get(i)) != null) {
                    Temp t = new Temp();
                    t.setCaseNumber(temp.get(i).getCaseNumber());
                    t.setVisitDate(temp.get(i).getVisitDate());
                    t.setDiagnosis(service.searchHostory(temp.get(i)).getDiagnosis());
                    t.setDeptName(service.searchDeptName(register));
                    result2.add(t);
                    System.out.println("register = " + register);
                    System.out.println("t = " + t.getVisitDate());
                }
            }

            System.out.println("结果：" + result2);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "查询失败";
        }

        return new ResultUtil(code, msg, result2);
    }

    /**
     * @return {@link ResultUtil}
     * @description 查询患者历史病历明细
     */
    @PostMapping("/outpatientDoctorWorkStation/outpatientMedicalRecordHomePage/historyRegisterDetail")
    public ResultUtil historyRegisterDetail(@RequestBody Register register) {
        System.out.println("细节：" + register);
        MedicalRecord result = new MedicalRecord();
        Integer code = EnumCode.OK.getValue();
        String msg = "查询细节成功";
        try {
            result = service.historyRegisterDetail(register);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "查询细节失败";
        }
        return new ResultUtil(code, msg, result);
    }
}

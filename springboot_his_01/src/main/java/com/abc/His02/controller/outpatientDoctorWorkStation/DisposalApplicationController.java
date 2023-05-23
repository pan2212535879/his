package com.abc.His02.controller.outpatientDoctorWorkStation;

import com.abc.His02.entity.CheckApply;
import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.service.outpatientDoctorWorkStationService.DisposalApplicationService;
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
 * @description 处置申请控制类
 */
@RestController
public class DisposalApplicationController {

    @Autowired
    private DisposalApplicationService service;

    /**
     * @return {@link ResultUtil}
     * @description 初始化待申请处置的患者
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/initPatients")
    public ResultUtil initPatients(@RequestBody User user) {
        System.out.println("传来的医生信息：" + user);
        // 1.先根据医生来找Register里符合要求的数据
        List<Register> caseNumber = new ArrayList<>();
        caseNumber = service.initPatients(user);
//        System.out.println("caseNumber = " + caseNumber);
        // 2.从上面数据里在MedicalRecord里找CaseState=2的数据
        class Temp extends MedicalRecord {
            private String RealName;//患者姓名
            private Integer Age;//年龄

            public String getRealName() {
                return RealName;
            }

            public void setRealName(String name) {
                RealName = name;
            }

            public Integer getAge() {
                return Age;
            }

            public void setAge(Integer age) {
                Age = age;
            }

            public Temp(String name, Integer age) {
                RealName = name;
                Age = age;
            }

            public Temp() {
            }
        }
        List<Temp> temp = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化病历信息成功";
        try {
            for (int i=0; i<caseNumber.size(); i++) {
                // System.out.println("caseNumber[" + i + "] = " + caseNumber.get(i).getCaseNumber() + caseNumber.get(i).getRealName() + caseNumber.get(i).getAge());
                // 3.查询每一个记录是否在MedicalRecord里，是就添加到新的类中，否就跳过
                if (service.initRecords(caseNumber.get(i).getCaseNumber()) != null) {//将患者病历号传入initRecords()函数中。
                    // System.out.println("查到的记录：" + service.initRecords(caseNumber.get(i).getCaseNumber()));
                    Temp t = new Temp();
                    t.setRealName(caseNumber.get(i).getRealName());
                    t.setAge(caseNumber.get(i).getAge());
                    t.setCaseNumber(caseNumber.get(i).getCaseNumber());
                    t.setID(service.initRecords(caseNumber.get(i).getCaseNumber()).getID());
                    t.setRegistID(service.initRecords(caseNumber.get(i).getCaseNumber()).getRegistID());
                    t.setReadme(service.initRecords(caseNumber.get(i).getCaseNumber()).getReadme());
                    t.setPresent(service.initRecords(caseNumber.get(i).getCaseNumber()).getPresent());
                    t.setPresentTreat(service.initRecords(caseNumber.get(i).getCaseNumber()).getPresentTreat());
                    t.setHistory(service.initRecords(caseNumber.get(i).getCaseNumber()).getHistory());
                    t.setAllergy(service.initRecords(caseNumber.get(i).getCaseNumber()).getAllergy());
                    t.setPhysique(service.initRecords(caseNumber.get(i).getCaseNumber()).getPhysique());
                    t.setProposal(service.initRecords(caseNumber.get(i).getCaseNumber()).getProposal());
                    t.setCareful(service.initRecords(caseNumber.get(i).getCaseNumber()).getCareful());
                    t.setCheckResult(service.initRecords(caseNumber.get(i).getCaseNumber()).getCheckResult());
                    t.setDiagnosis(service.initRecords(caseNumber.get(i).getCaseNumber()).getDiagnosis());
                    t.setHandling(service.initRecords(caseNumber.get(i).getCaseNumber()).getHandling());
                    t.setCaseState(service.initRecords(caseNumber.get(i).getCaseNumber()).getCaseState());
                    System.out.println("符合要求的数据：" + t);
                    temp.add(t);
                }
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "初始化病历信息失败";
        }

        // 4.返回自定义的类对象，包含姓名和年龄
        System.out.println("即将返回的数据：" + temp);

        return new ResultUtil(code, msg, temp);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化患者的处置申请信息
     */
    @RequestMapping("/outpatientDoctorWorkStation/disposalApplicationService/initApply")
    public ResultUtil initApply(MedicalRecord medicalRecord) {
        System.out.println("即将查询的处置申请的患者：" + medicalRecord);
        List<CheckApply> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        boolean flag = true;
        String msg = "已加载处置申请信息";
        try {
            if (service.applyIsExist(medicalRecord) == null) {
                flag = false;
                msg = "此患者暂无处置申请信息";
            } else {
                result = service.getApply(medicalRecord);
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "加载处置信息失败";
        }
        if (flag) {
            return new ResultUtil(code, msg, result);
        } else {
            return new ResultUtil(code, msg);
        }


    }

    /**
     * @return {@link ResultUtil}
     * @description 暂存患者的处置项目
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/tempApply")
    public ResultUtil tempApply(@RequestBody CheckApply checkApply) {
        System.out.println("即将暂存的项目：" + checkApply);
        Integer code = EnumCode.OK.getValue();
        String msg = "暂存成功";
        try {
            // 1.先在数据库里找是否有这个项目
            if (service.checkApplyIsExist(checkApply) == null) {
                System.out.println("无新项目，插入");
                // 2.没有则插入新纪录
                service.insertCheckApply(checkApply);
            } else {
                // 3.否则更新
                System.out.println("有新项目，更新");
                service.updateCheckApply(checkApply);
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "暂存失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 开立患者的处置项目
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/commitApply")
    public ResultUtil commitApply(@RequestBody CheckApply checkApply) {
        System.out.println("即将开立的项目：" + checkApply);
        Integer code = EnumCode.OK.getValue();
        String msg = "开立成功";
        try {
            service.updateCheckApply(checkApply);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "开立失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 删除患者的处置项目
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/deleteApply")
    public ResultUtil deleteApply(@RequestBody CheckApply checkApply) {
        System.out.println("即将删除的项目：" + checkApply);
        Integer code = EnumCode.OK.getValue();
        String msg = "删除成功";
        try {
            service.deleteCheckApply(checkApply);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "删除失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 作废患者的处置项目
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/cancelApply")
    public ResultUtil cancelApply(@RequestBody CheckApply checkApply) {
        System.out.println("即将作废的项目：" + checkApply);
        Integer code = EnumCode.OK.getValue();
        String msg = "作废成功";
        try {
            service.updateCheckApply(checkApply);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "开立失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 修改患者的处置项目
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/editApply")
    public ResultUtil editApply(@RequestBody CheckApply checkApply) {
        System.out.println("即将修改的项目：" + checkApply);
        Integer code = EnumCode.OK.getValue();
        String msg = "修改成功";
        try {
            service.updateCheckApply(checkApply);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "修改失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成项目的名称
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/initItemName")
    public ResultUtil initItemName() {
        List<?> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "项目名称生成成功";
        try {
            result = service.initItemName();
            System.out.println("即将返回的项目：" + result);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "项目名称生成失败";
        }

        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 添加患者的处置项目
     */
    @PostMapping("/outpatientDoctorWorkStation/disposalApplicationService/addApply")
    public ResultUtil addApply(@RequestBody CheckApply checkApply) {
        System.out.println("即将添加的项目：" + checkApply);
        Integer code = EnumCode.OK.getValue();
        String msg = "添加成功";
        try {
            service.addApply(checkApply);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "添加失败";
        }

        return new ResultUtil(code, msg);
    }
}

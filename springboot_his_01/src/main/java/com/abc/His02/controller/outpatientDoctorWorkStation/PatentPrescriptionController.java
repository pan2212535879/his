package com.abc.His02.controller.outpatientDoctorWorkStation;

import com.abc.His02.entity.*;
import com.abc.His02.service.outpatientDoctorWorkStationService.PatentPrescriptionService;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @description 成药处方控制类
 */
@RestController
public class PatentPrescriptionController {

    @Autowired
    private PatentPrescriptionService service;

    /**
     * @return {@link ResultUtil}
     * @description 初始化患者信息
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/initPatients")
    public ResultUtil initPatients(@RequestBody User user) {
        System.out.println("开始初始化病人，传来的医生信息：" + user);
        List<Register> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化病人数据成功";
        try {
            result = service.initPatients(user);
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
    @RequestMapping("/outpatientDoctorWorkStation/patentPrescription/searchByName")
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
    @RequestMapping("/outpatientDoctorWorkStation/patentPrescription/searchByNumber")
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
     * @description 初始化患者的成药处方
     */
    @RequestMapping("/outpatientDoctorWorkStation/patentPrescription/initPrescription")
    public ResultUtil initRecord(MedicalRecord user) {
        System.out.println("初始化的病历患者：" + user);
        List<Prescription> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "已加载患者成药处方列表";
        try {
            // 1.根据病历号和病历状态查病历ID、挂号ID
            Integer medicalID = service.findMedicalID(user);
            Integer registID = service.findRegistID(user);
            // 2.根据查到的病历ID和挂号ID查成药处方表，注意一个患者的成药处方表不止一个，一对多
            System.out.println("查到的Medical ID和Regist ID：" + medicalID + "," + registID);
            result = service.initPrescription(medicalID, registID);
            System.out.println(result);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "加载患者成药处方列表失败";
        }

        // 3.返回成药处方列表
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 暂存患者的处方
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/tempPrescription")
    public ResultUtil tempPrescription(@RequestBody Prescription prescription) {
        System.out.println("即将暂存的处方：" + prescription);
        Integer code = EnumCode.OK.getValue();
        String msg = "暂存成功";
        try {
            // 1.先在数据库里找是否有这个项目
            if (service.prescriptionIsExist(prescription) == null) {
                System.out.println("无新处方，插入");
                // 2.没有则插入新纪录
                service.insertPrescription(prescription);
            } else {
                // 3.否则更新
                System.out.println("有新处方，更新");
                service.updatePrescription(prescription);
            }
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "暂存失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 发送患者的处方
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/sendPrescription")
    public ResultUtil sendPrescription(@RequestBody Prescription prescription) {
        System.out.println("即将发送的处方：" + prescription);
        Integer code = EnumCode.OK.getValue();
        String msg = "发送成功";
        try {
            service.updatePrescription(prescription);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "发送失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 开立患者的处方
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/commitPrescription")
    public ResultUtil commitPrescription(@RequestBody Prescription prescription) {
        System.out.println("即将开立的处方：" + prescription);
        Integer code = EnumCode.OK.getValue();
        String msg = "开立成功";
        try {
            service.updatePrescription(prescription);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "开立失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 删除患者的处方
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/deletePrescription")
    public ResultUtil deletePrescription(@RequestBody Prescription prescription) {
        System.out.println("即将删除的处方：" + prescription);
        Integer code = EnumCode.OK.getValue();
        String msg = "删除成功";
        try {
            service.deletePrescription(prescription);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "删除失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 作废患者的处方
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/cancelPrescription")
    public ResultUtil cancelPrescription(@RequestBody Prescription prescription) {
        System.out.println("即将作废的处方：" + prescription);
        Integer code = EnumCode.OK.getValue();
        String msg = "作废成功";
        try {
            service.updatePrescription(prescription);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "作废失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 获取患者的挂号ID
     */
    @RequestMapping("/outpatientDoctorWorkStation/patentPrescription/getMedicalID")
    public ResultUtil getMedicalID(MedicalRecord medicalRecord) {
        System.out.println("传来的caseNumber：" + medicalRecord);
        Integer result = null;
        Integer code = EnumCode.OK.getValue();
        String msg = "转换成功";
        try {
            result = service.getMedicalID(medicalRecord);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "转换失败";
        }
        System.out.println("找到的ID：" + result);
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 新增患者的处方
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/addPrescription")
    public ResultUtil addPrescription(@RequestBody Prescription prescription) {
        System.out.println("即将新增的处方：" + prescription);
        Integer code = EnumCode.OK.getValue();
        String msg = "新增成功";
        try {
            service.insertPrescription(prescription);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "新增失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 查询处方明细
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/initDetail")
    public ResultUtil initDetail(@RequestBody Prescription prescription) {
        System.out.println("即将查询的处方：" + prescription);
        // 1.根据处方查询明细
        List<PrescriptionDetailed> temp = service.initDetail(prescription);
        // 2.把每一个明细赋值给新的类
        class NewPrescriptionDetailed extends PrescriptionDetailed {
            private String DrugsName;

            public String getDrugsName() {
                return DrugsName;
            }

            public void setDrugsName(String drugsName) {
                DrugsName = drugsName;
            }

            public NewPrescriptionDetailed(String drugsName) {
                DrugsName = drugsName;
            }

            public NewPrescriptionDetailed() {
            }
        }
        List<NewPrescriptionDetailed> newPrescriptionDetaileds = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "初始化处方明细成功";
        try {
            for (int i = 0; i < temp.size(); i++) {
                NewPrescriptionDetailed t = new NewPrescriptionDetailed();
                t.setDrugsName(service.initName(temp.get(i).getDrugsID()));
                System.out.println("ID:" + temp.get(i).getDrugsID() + ", Name:" + t.getDrugsName());
                t.setID(temp.get(i).getID());
                t.setPrescriptionID(temp.get(i).getPrescriptionID());
                t.setDrugsID(temp.get(i).getDrugsID());
                t.setDrugsUsage(temp.get(i).getDrugsUsage());
                t.setDosage(temp.get(i).getDosage());
                t.setFrequency(temp.get(i).getFrequency());
                t.setAmount(temp.get(i).getAmount());
                t.setState(temp.get(i).getState());
                newPrescriptionDetaileds.add(t);
            }
            // 3.返回新的类
            System.out.println("即将返回的结果：" + newPrescriptionDetaileds);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "初始化处方明细失败";
        }

        return new ResultUtil(code, msg, newPrescriptionDetaileds);
    }

    /**
     * @return {@link ResultUtil}
     * @description 初始化药品列表
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/initDrugsPrepare")
    public ResultUtil initDrugsPrepare() {
        List<Map<Integer, String>> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成药品成功";
        try {
            result = service.initDrugsPrepare();
            System.out.println("找到的药品：" + result);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成药品失败";
        }

        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 增加药品
     */
    @RequestMapping("/outpatientDoctorWorkStation/patentPrescription/addDrugs")
    public ResultUtil addDrugs(PrescriptionDetailed prescriptionDetailed) {
        System.out.println("即将添加的药品：" + prescriptionDetailed);
        Integer code = EnumCode.OK.getValue();
        String msg = "增药成功";
        try {
            service.addDrugs(prescriptionDetailed);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "增药失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 删除药品
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/deleteDrugs")
    public ResultUtil deleteDrugs(@RequestBody PrescriptionDetailed prescriptionDetailed) {
        System.out.println("即将删除的药：" + prescriptionDetailed);
        Integer code = EnumCode.OK.getValue();
        String msg = "删药成功";
        try {
            service.deleteDrugs(prescriptionDetailed);
        } catch (Exception e) {
            code = EnumCode.INTERNAL_SERVER_ERROR.getValue();
            msg = "删药失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成成药模板(个人)
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/getDrugsTemplatePersonally")
    public ResultUtil getDrugsTemplatePersonally(@RequestBody User user) {
        System.out.println("user = " + user);
        List<DrugsTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成个人成药模板成功";
        try {
            result = service.getDrugsTemplatePersonally(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成个人成药模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成成药模板(科室)
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/getDrugsTemplateByDepartment")
    public ResultUtil getDrugsTemplateByDepartment(@RequestBody User user) {
        System.out.println("user = " + user);
        List<DrugsTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成科室成药模板成功";
        try {
            result = service.getDrugsTemplateByDepartment(user);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成科室成药模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成成药模板(全院)
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/getDrugsTemplateByHospital")
    public ResultUtil getDrugsTemplateByHospital() {
        List<DrugsTemplate> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成全院成药模板成功";
        try {
            result = service.getDrugsTemplateByHospital();
        } catch (Exception e) {
            code = EnumCode.INTERNAL_SERVER_ERROR.getValue();
            msg = "生成全院成药模板失败";
        }
        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 生成成药明细
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/chooseTemplate")
    public ResultUtil chooseTemplate(@RequestBody DrugsTemplate drugsTemplate) {
        System.out.println("选择的模板：" + drugsTemplate);
        List<DrugsDetailed> result = new ArrayList<>();
        Integer code = EnumCode.OK.getValue();
        String msg = "生成成药明细成功";
        try {
            result = service.chooseTemplate(drugsTemplate);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "生成成药明细失败";
        }

        return new ResultUtil(code, msg, result);
    }

    /**
     * @return {@link ResultUtil}
     * @description 删除成药模板
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/deleteTemplate")
    public ResultUtil deleteTemplate(@RequestBody DrugsTemplate drugsTemplate) {
        System.out.println("删除的模板：" + drugsTemplate);
        Integer code = EnumCode.OK.getValue();
        String msg = "删除成功";
        try {
            service.deleteTemplate(drugsTemplate);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "删除失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 添加成药模板
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/addTemplateConfirm")
    public ResultUtil addTemplateConfirm(@RequestBody DrugsTemplate drugsTemplate) {
        System.out.println("即将添加的模板：" + drugsTemplate);
        Integer code = EnumCode.OK.getValue();
        String msg = "删除成功";
        try {
            service.addTemplateConfirm(drugsTemplate);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "删除失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 删除成药模板明细
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/deleteTemplateDetail")
    public ResultUtil deleteTemplateDetail(@RequestBody DrugsDetailed drugsDetailed) {
        System.out.println("即将删除的模板明细：" + drugsDetailed);
        Integer code = EnumCode.OK.getValue();
        String msg = "删除成功";
        try {
            service.deleteTemplateDetail(drugsDetailed);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "删除失败";
        }

        return new ResultUtil(code, msg);
    }

    /**
     * @return {@link ResultUtil}
     * @description 添加成药模板明细
     */
    @PostMapping("/outpatientDoctorWorkStation/patentPrescription/addTemplateDetailConfirm")
    public ResultUtil addTemplateDetailConfirm(@RequestBody DrugsDetailed drugsDetailed) {
        System.out.println("即将添加的明细模板：" + drugsDetailed);
        Integer code = EnumCode.OK.getValue();
        String msg = "添加成功";
        try {
            service.addTemplateDetailConfirm(drugsDetailed);
        } catch (Exception e) {
            code = EnumCode.EXCPTION_ERROR.getValue();
            msg = "添加失败";
        }

        return new ResultUtil(code, msg);
    }
}

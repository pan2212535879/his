package com.abc.His02.controller.menus;

import com.abc.His02.entity.User;
import com.abc.His02.entity.menu.Menu;
import com.abc.His02.util.EnumCode;
import com.abc.His02.util.ResultUtil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MenuController {
    public List<Menu> adminMenu() { //医院管理员的菜单
        List<Menu> adminMenuList = new ArrayList<>(); //医院管理员的总菜单

        Menu departmentManagement = new Menu(); //第一个子级菜单
        departmentManagement.setName("DepartmentManage");
        departmentManagement.setTitle("科室管理");
        departmentManagement.setIcon("");
        departmentManagement.setPath("/admin/departmentManage");
        departmentManagement.setComponent("admin/DepartmentManage");
        departmentManagement.setChildren(null);

        Menu userManagement = new Menu(); //第二个子级菜单
        userManagement.setName("UserManage");
        userManagement.setTitle("用户管理");
        userManagement.setIcon("");
        userManagement.setPath("/admin/userManage");
        userManagement.setComponent("admin/UserManage");
        userManagement.setChildren(null);

        Menu registrationLevelManagement = new Menu(); //第三个子级菜单
        registrationLevelManagement.setName("RegistrationLevelManagement");
        registrationLevelManagement.setTitle("挂号级别管理");
        registrationLevelManagement.setIcon("");
        registrationLevelManagement.setPath("/admin/registrationLevelManagement");
        registrationLevelManagement.setComponent("admin/RegistrationLevelManagement");
        registrationLevelManagement.setChildren(null);

        Menu settlementCategoryManagement = new Menu(); //第四个子级菜单
        settlementCategoryManagement.setName("SettlementCategoryManagement");
        settlementCategoryManagement.setTitle("结算类别管理");
        settlementCategoryManagement.setIcon("");
        settlementCategoryManagement.setPath("/admin/settlementCategoryManagement");
        settlementCategoryManagement.setComponent("admin/SettlementCategoryManagement");
        settlementCategoryManagement.setChildren(null);

        Menu diagnosticDirectoryManagement = new Menu(); //第五个子级菜单
        diagnosticDirectoryManagement.setName("diagnosticDirectoryManagement");
        diagnosticDirectoryManagement.setTitle("诊断目录管理");
        diagnosticDirectoryManagement.setIcon("");
        diagnosticDirectoryManagement.setPath("/admin/diagnosticDirectoryManagement");
        diagnosticDirectoryManagement.setComponent("admin/DiagnosticDirectoryManagement");
        diagnosticDirectoryManagement.setChildren(null);

        Menu nonDrugChargeItemsManagement = new Menu(); //第六个子级菜单
        nonDrugChargeItemsManagement.setName("nonDrugChargeItemsManagement");
        nonDrugChargeItemsManagement.setTitle("非药品收费项目管理");
        nonDrugChargeItemsManagement.setIcon("");
        nonDrugChargeItemsManagement.setPath("/admin/nonDrugChargeItemsManagement");
        nonDrugChargeItemsManagement.setComponent("admin/NonDrugChargeItemsManagement");
        nonDrugChargeItemsManagement.setChildren(null);

        Menu doctorSchedulingManagement = new Menu(); //第七个子级菜单
        doctorSchedulingManagement.setName("doctorSchedulingManagement");
        doctorSchedulingManagement.setTitle("医生排班管理");
        doctorSchedulingManagement.setIcon("");
        doctorSchedulingManagement.setPath("/admin/doctorSchedulingManagement");
        doctorSchedulingManagement.setComponent("admin/DoctorSchedulingManagement");
        doctorSchedulingManagement.setChildren(null);

        Menu basicInformationMaintenance = new Menu(); //第一个父级菜单
        basicInformationMaintenance.setName("BasicInformationMaintenance");
        basicInformationMaintenance.setTitle("基础信息维护");
        basicInformationMaintenance.setIcon("");
        basicInformationMaintenance.setPath("");
        basicInformationMaintenance.setComponent("");

        List<Menu> allChildren = new ArrayList<>();
        allChildren.add(departmentManagement);
        allChildren.add(userManagement);
        allChildren.add(registrationLevelManagement);
        allChildren.add(settlementCategoryManagement);
        allChildren.add(diagnosticDirectoryManagement);
        allChildren.add(nonDrugChargeItemsManagement);
        allChildren.add(doctorSchedulingManagement);
        basicInformationMaintenance.setChildren(allChildren);

        adminMenuList.add(basicInformationMaintenance);

        return adminMenuList;
    }

    public List<Menu> registeredTollCollectorMenu() { //挂号收费员的菜单
        List<Menu> registeredTollCollectorMenuList = new ArrayList<>(); //挂号收费员的总菜单

        Menu onSiteRegistration = new Menu(); //第一个子级菜单
        onSiteRegistration.setName("OnSiteRegistration");
        onSiteRegistration.setTitle("现场挂号");
        onSiteRegistration.setIcon("");
        onSiteRegistration.setPath("/registeredTollCollector/onSiteRegistration");
        onSiteRegistration.setComponent("registeredTollCollector/OnSiteRegistration");
        onSiteRegistration.setChildren(null);

        Menu charge = new Menu(); //第二个子级菜单
        charge.setName("Charge");
        charge.setTitle("收费");
        charge.setIcon("");
        charge.setPath("/registeredTollCollector/charge");
        charge.setComponent("registeredTollCollector/Charge");
        charge.setChildren(null);

        Menu backNumber = new Menu(); //第三个子级菜单
        backNumber.setName("BackNumber");
        backNumber.setTitle("退号");
        backNumber.setIcon("");
        backNumber.setPath("/registeredTollCollector/backNumber");
        backNumber.setComponent("registeredTollCollector/BackNumber");
        backNumber.setChildren(null);

//        Menu refund = new Menu(); //第四个子级菜单
//        refund.setName("Refund");
//        refund.setTitle("退费");
//        refund.setIcon("");
//        refund.setPath("/registeredTollCollector/refund");
//        refund.setComponent("registeredTollCollector/Refund");
//        refund.setChildren(null);

        // Menu patientExpenseQuery = new Menu(); //第五个子级菜单
        // patientExpenseQuery.setName("PatientExpenseQuery");
        // patientExpenseQuery.setTitle("患者费用查询");
        // patientExpenseQuery.setIcon("");
        // patientExpenseQuery.setPath("/registeredTollCollector/patientExpenseQuery");
        // patientExpenseQuery.setComponent("registeredTollCollector/PatientExpenseQuery");
        // patientExpenseQuery.setChildren(null);

        // Menu tollCollectorDailySettlement = new Menu(); //第六个子级菜单
        // tollCollectorDailySettlement.setName("TollCollectorDailySettlement");
        // tollCollectorDailySettlement.setTitle("收费员日结");
        // tollCollectorDailySettlement.setIcon("");
        // tollCollectorDailySettlement.setPath("/registeredTollCollector/tollCollectorDailySettlement");
        // tollCollectorDailySettlement.setComponent("registeredTollCollector/TollCollectorDailySettlement");
        // tollCollectorDailySettlement.setChildren(null);

//        Menu supplementaryPrintingOfInvoice = new Menu(); //第七个子级菜单
//        supplementaryPrintingOfInvoice.setName("SupplementaryPrintingOfInvoice");
//        supplementaryPrintingOfInvoice.setTitle("发票补打");
//        supplementaryPrintingOfInvoice.setIcon("");
//        supplementaryPrintingOfInvoice.setPath("/registeredTollCollector/supplementaryPrintingOfInvoice");
//        supplementaryPrintingOfInvoice.setComponent("registeredTollCollector/SupplementaryPrintingOfInvoice");
//        supplementaryPrintingOfInvoice.setChildren(null);
//
//        Menu invoiceReprint = new Menu(); //第八个子级菜单
//        invoiceReprint.setName("InvoiceReprint");
//        invoiceReprint.setTitle("发票重打");
//        invoiceReprint.setIcon("");
//        invoiceReprint.setPath("/registeredTollCollector/invoiceReprint");
//        invoiceReprint.setComponent("registeredTollCollector/InvoiceReprint");
//        invoiceReprint.setChildren(null);

        Menu outpatientRegistrationFee = new Menu(); //第一个父级菜单
        outpatientRegistrationFee.setName("OutpatientRegistrationFee");
        outpatientRegistrationFee.setTitle("门诊挂号收费");
        outpatientRegistrationFee.setIcon("");
        outpatientRegistrationFee.setPath("");
        outpatientRegistrationFee.setComponent("");

        List<Menu> allChildren = new ArrayList<>();
        allChildren.add(onSiteRegistration);
        allChildren.add(charge);
        allChildren.add(backNumber);
//        allChildren.add(refund);
//         allChildren.add(patientExpenseQuery);
        // allChildren.add(tollCollectorDailySettlement);
//        allChildren.add(supplementaryPrintingOfInvoice);
//        allChildren.add(invoiceReprint);
        outpatientRegistrationFee.setChildren(allChildren);

        registeredTollCollectorMenuList.add(outpatientRegistrationFee);

        return registeredTollCollectorMenuList;
    }

    public List<Menu> outpatientDoctorWorkstationMenu() { //门诊医生的菜单
        List<Menu> outpatientDoctorWorkstationMenuList = new ArrayList<>(); //门诊医生的总菜单

        Menu outpatientMedicalRecordHomePage = new Menu(); //第一个子级菜单
        outpatientMedicalRecordHomePage.setName("OutpatientMedicalRecordHomePage");
        outpatientMedicalRecordHomePage.setTitle("门诊病历首页");
        outpatientMedicalRecordHomePage.setIcon("");
        outpatientMedicalRecordHomePage.setPath("/outpatientDoctorWorkstation/outpatientMedicalRecordHomePage");
        outpatientMedicalRecordHomePage.setComponent("outpatientDoctorWorkstation/OutpatientMedicalRecordHomePage");
        outpatientMedicalRecordHomePage.setChildren(null);

        Menu inspectionApplication = new Menu(); //第二个子级菜单
        inspectionApplication.setName("InspectionApplication");
        inspectionApplication.setTitle("检查申请");
        inspectionApplication.setIcon("");
        inspectionApplication.setPath("/outpatientDoctorWorkstation/inspectionApplication");
        inspectionApplication.setComponent("outpatientDoctorWorkstation/InspectionApplication");
        inspectionApplication.setChildren(null);

//        Menu testApplication = new Menu(); //第三个子级菜单
//        testApplication.setName("testApplication");
//        testApplication.setTitle("检验申请");
//        testApplication.setIcon("");
//        testApplication.setPath("/outpatientDoctorWorkstation/testApplication");
//        testApplication.setComponent("outpatientDoctorWorkstation/TestApplication");
//        testApplication.setChildren(null);

        Menu outpatientDiagnosis = new Menu(); //第四个子级菜单
        outpatientDiagnosis.setName("OutpatientDiagnosis");
        outpatientDiagnosis.setTitle("门诊确诊");
        outpatientDiagnosis.setIcon("");
        outpatientDiagnosis.setPath("/outpatientDoctorWorkstation/outpatientDiagnosis");
        outpatientDiagnosis.setComponent("outpatientDoctorWorkstation/OutpatientDiagnosis");
        outpatientDiagnosis.setChildren(null);

        Menu disposalApplication = new Menu(); //第五个子级菜单
        disposalApplication.setName("DisposalApplication");
        disposalApplication.setTitle("处置申请");
        disposalApplication.setIcon("");
        disposalApplication.setPath("/outpatientDoctorWorkstation/disposalApplication");
        disposalApplication.setComponent("outpatientDoctorWorkstation/DisposalApplication");
        disposalApplication.setChildren(null);

        Menu patentPrescription = new Menu(); //第六个子级菜单
        patentPrescription.setName("PatentPrescription");
        patentPrescription.setTitle("成药处方");
        patentPrescription.setIcon("");
        patentPrescription.setPath("/outpatientDoctorWorkstation/patentPrescription");
        patentPrescription.setComponent("outpatientDoctorWorkstation/PatentPrescription");
        patentPrescription.setChildren(null);

//        Menu herbalPrescription = new Menu(); //第七个子级菜单
//        herbalPrescription.setName("HerbalPrescription");
//        herbalPrescription.setTitle("草药处方");
//        herbalPrescription.setIcon("");
//        herbalPrescription.setPath("/outpatientDoctorWorkstation/herbalPrescription");
//        herbalPrescription.setComponent("outpatientDoctorWorkstation/HerbalPrescription");
//        herbalPrescription.setChildren(null);

        Menu afterDiagnosis = new Menu(); //第八个子级菜单
        afterDiagnosis.setName("AfterDiagnosis");
        afterDiagnosis.setTitle("诊毕");
        afterDiagnosis.setIcon("");
        afterDiagnosis.setPath("/outpatientDoctorWorkstation/afterDiagnosis");
        afterDiagnosis.setComponent("outpatientDoctorWorkstation/AfterDiagnosis");
        afterDiagnosis.setChildren(null);

        Menu patientExpenseDetailsQuery = new Menu(); //第九个子级菜单
        patientExpenseDetailsQuery.setName("PatientExpenseDetailsQuery");
        patientExpenseDetailsQuery.setTitle("患者费用明细查询");
        patientExpenseDetailsQuery.setIcon("");
        patientExpenseDetailsQuery.setPath("/outpatientDoctorWorkstation/patientExpenseDetailsQuery");
        patientExpenseDetailsQuery.setComponent("outpatientDoctorWorkstation/PatientExpenseDetailsQuery");
        patientExpenseDetailsQuery.setChildren(null);

//        Menu medicalRecordTemplateManagement = new Menu(); //第十个子级菜单
//        medicalRecordTemplateManagement.setName("MedicalRecordTemplateManagement");
//        medicalRecordTemplateManagement.setTitle("病历模板管理");
//        medicalRecordTemplateManagement.setIcon("");
//        medicalRecordTemplateManagement.setPath("/outpatientDoctorWorkstation/medicalRecordTemplateManagement");
//        medicalRecordTemplateManagement.setComponent("outpatientDoctorWorkstation/MedicalRecordTemplateManagement");
//        medicalRecordTemplateManagement.setChildren(null);

        Menu inspectionTemplateManagement = new Menu(); //第十一个子级菜单
        inspectionTemplateManagement.setName("InspectionTemplateManagement");
        inspectionTemplateManagement.setTitle("检查模板管理");
        inspectionTemplateManagement.setIcon("");
        inspectionTemplateManagement.setPath("/outpatientDoctorWorkstation/inspectionTemplateManagement");
        inspectionTemplateManagement.setComponent("outpatientDoctorWorkstation/InspectionTemplateManagement");
        inspectionTemplateManagement.setChildren(null);

//        Menu testTemplateManagement = new Menu(); //第十二个子级菜单
//        testTemplateManagement.setName("TestTemplateManagement");
//        testTemplateManagement.setTitle("检验模板管理");
//        testTemplateManagement.setIcon("");
//        testTemplateManagement.setPath("/outpatientDoctorWorkstation/testTemplateManagement");
//        testTemplateManagement.setComponent("outpatientDoctorWorkstation/TestTemplateManagement");
//        testTemplateManagement.setChildren(null);

//        Menu disposalTemplateManagement = new Menu(); //第十三个子级菜单
//        disposalTemplateManagement.setName("DisposalTemplateManagement");
//        disposalTemplateManagement.setTitle("处置模板管理");
//        disposalTemplateManagement.setIcon("");
//        disposalTemplateManagement.setPath("/outpatientDoctorWorkstation/disposalTemplateManagement");
//        disposalTemplateManagement.setComponent("outpatientDoctorWorkstation/DisposalTemplateManagement");
//        disposalTemplateManagement.setChildren(null);

//        Menu prescriptionSetManagement = new Menu(); //第十四个子级菜单
//        prescriptionSetManagement.setName("PrescriptionSetManagement");
//        prescriptionSetManagement.setTitle("处方组套管理");
//        prescriptionSetManagement.setIcon("");
//        prescriptionSetManagement.setPath("/outpatientDoctorWorkstation/prescriptionSetManagement");
//        prescriptionSetManagement.setComponent("outpatientDoctorWorkstation/PrescriptionSetManagement");
//        prescriptionSetManagement.setChildren(null);

        Menu personalWorkloadStatistics = new Menu(); //第十五个子级菜单
        personalWorkloadStatistics.setName("PersonalWorkloadStatistics");
        personalWorkloadStatistics.setTitle("个人工作量统计");
        personalWorkloadStatistics.setIcon("");
        personalWorkloadStatistics.setPath("/outpatientDoctorWorkstation/personalWorkloadStatistics");
        personalWorkloadStatistics.setComponent("outpatientDoctorWorkstation/PersonalWorkloadStatistics");
        personalWorkloadStatistics.setChildren(null);

        Menu outpatientDoctorWorkstation = new Menu(); //第一个父级菜单
        outpatientDoctorWorkstation.setName("OutpatientDoctorWorkstation");
        outpatientDoctorWorkstation.setTitle("门诊医生工作站");
        outpatientDoctorWorkstation.setIcon("");
        outpatientDoctorWorkstation.setPath("");
        outpatientDoctorWorkstation.setComponent("");

        List<Menu> allChildren = new ArrayList<>();
        allChildren.add(outpatientMedicalRecordHomePage);
        allChildren.add(inspectionApplication);
//        allChildren.add(testApplication);
        allChildren.add(outpatientDiagnosis);
        allChildren.add(disposalApplication);
        allChildren.add(patentPrescription);
//        allChildren.add(herbalPrescription);
        allChildren.add(afterDiagnosis);
        allChildren.add(patientExpenseDetailsQuery);
//        allChildren.add(medicalRecordTemplateManagement);
        allChildren.add(inspectionTemplateManagement);
//        allChildren.add(testTemplateManagement);
//        allChildren.add(disposalTemplateManagement);
//        allChildren.add(prescriptionSetManagement);
        allChildren.add(personalWorkloadStatistics);
        outpatientDoctorWorkstation.setChildren(allChildren);

        outpatientDoctorWorkstationMenuList.add(outpatientDoctorWorkstation);

        return outpatientDoctorWorkstationMenuList;
    }

    public List<Menu> outpatientMedicalTechnologyWorkstationMenu() { //门诊医技的菜单
        List<Menu> outpatientMedicalTechnologyWorkstationMenuList = new ArrayList<>(); //门诊医技的总菜单

        Menu inspectionAndTestRegister = new Menu(); //第一个子级菜单
        inspectionAndTestRegister.setName("InspectionAndTestRegister");
        inspectionAndTestRegister.setTitle("检查/检验登记");
        inspectionAndTestRegister.setIcon("");
        inspectionAndTestRegister.setPath("/outpatientMedicalTechnologyWorkstation/inspectionAndTestRegister");
        inspectionAndTestRegister.setComponent("outpatientMedicalTechnologyWorkstation/InspectionAndTestRegister");
        inspectionAndTestRegister.setChildren(null);

        Menu inspectionAndTestResultEntry = new Menu(); //第二个子级菜单
        inspectionAndTestResultEntry.setName("InspectionAndTestResultEntry");
        inspectionAndTestResultEntry.setTitle("检查/检验结果录入");
        inspectionAndTestResultEntry.setIcon("");
        inspectionAndTestResultEntry.setPath("/outpatientMedicalTechnologyWorkstation/inspectionAndTestResultEntry");
        inspectionAndTestResultEntry.setComponent("outpatientMedicalTechnologyWorkstation/InspectionAndTestResultEntry");
        inspectionAndTestResultEntry.setChildren(null);

        Menu personalWorkloadStatistics = new Menu(); //第三个子级菜单
        personalWorkloadStatistics.setName("PersonalWorkloadStatistics");
        personalWorkloadStatistics.setTitle("个人工作量统计");
        personalWorkloadStatistics.setIcon("");
        personalWorkloadStatistics.setPath("/outpatientMedicalTechnologyWorkstation/personalWorkloadStatistics");
        personalWorkloadStatistics.setComponent("outpatientMedicalTechnologyWorkstation/PersonalWorkloadStatistics");
        personalWorkloadStatistics.setChildren(null);

        Menu outpatientMedicalTechnologyWorkstation = new Menu(); //第一个父级菜单
        outpatientMedicalTechnologyWorkstation.setName("OutpatientMedicalTechnologyWorkstation");
        outpatientMedicalTechnologyWorkstation.setTitle("门诊医技工作站");
        outpatientMedicalTechnologyWorkstation.setIcon("");
        outpatientMedicalTechnologyWorkstation.setPath("");
        outpatientMedicalTechnologyWorkstation.setComponent("");

        List<Menu> allChildren = new ArrayList<>();
        allChildren.add(inspectionAndTestRegister);
        allChildren.add(inspectionAndTestResultEntry);
        allChildren.add(personalWorkloadStatistics);
        outpatientMedicalTechnologyWorkstation.setChildren(allChildren);

        outpatientMedicalTechnologyWorkstationMenuList.add(outpatientMedicalTechnologyWorkstation);

        return outpatientMedicalTechnologyWorkstationMenuList;
    }

    public List<Menu> outpatientPharmacyWorkstationMenu() { //门诊药房的菜单
        List<Menu> outpatientPharmacyWorkstationMenuList = new ArrayList<>(); //门诊药房的总菜单

        Menu outpatientDispensing = new Menu(); //第一个子级菜单
        outpatientDispensing.setName("OutpatientDispensing");
        outpatientDispensing.setTitle("门诊发药");
        outpatientDispensing.setIcon("");
        outpatientDispensing.setPath("/outpatientPharmacyWorkstation/outpatientDispensing");
        outpatientDispensing.setComponent("outpatientPharmacyWorkstation/OutpatientDispensing");
        outpatientDispensing.setChildren(null);

        Menu outpatientWithdrawal = new Menu(); //第二个子级菜单
        outpatientWithdrawal.setName("OutpatientWithdrawal");
        outpatientWithdrawal.setTitle("门诊退药");
        outpatientWithdrawal.setIcon("");
        outpatientWithdrawal.setPath("/outpatientPharmacyWorkstation/outpatientWithdrawal");
        outpatientWithdrawal.setComponent("outpatientPharmacyWorkstation/OutpatientWithdrawal");
        outpatientWithdrawal.setChildren(null);

        Menu drugCatalogueManagement = new Menu(); //第三个子级菜单
        drugCatalogueManagement.setName("DrugCatalogueManagement");
        drugCatalogueManagement.setTitle("药品目录管理");
        drugCatalogueManagement.setIcon("");
        drugCatalogueManagement.setPath("/outpatientPharmacyWorkstation/drugCatalogueManagement");
        drugCatalogueManagement.setComponent("outpatientPharmacyWorkstation/DrugCatalogueManagement");
        drugCatalogueManagement.setChildren(null);

        Menu outpatientPharmacyWorkstation = new Menu(); //第一个父级菜单
        outpatientPharmacyWorkstation.setName("OutpatientPharmacyWorkstation");
        outpatientPharmacyWorkstation.setTitle("门诊药房工作站");
        outpatientPharmacyWorkstation.setIcon("");
        outpatientPharmacyWorkstation.setPath("");
        outpatientPharmacyWorkstation.setComponent("");

        List<Menu> allChildren = new ArrayList<>();
        allChildren.add(outpatientDispensing);
        allChildren.add(outpatientWithdrawal);
        allChildren.add(drugCatalogueManagement);
        outpatientPharmacyWorkstation.setChildren(allChildren);

        outpatientPharmacyWorkstationMenuList.add(outpatientPharmacyWorkstation);

        return outpatientPharmacyWorkstationMenuList;
    }

    public List<Menu> patientMenu() { //患者的菜单
        List<Menu> patientMenuList = new ArrayList<>(); //患者的总菜单

        Menu register = new Menu(); //第一个子级菜单
        register.setName("register");
        register.setTitle("挂号");
        register.setIcon("");
        register.setPath("/patient/register");
        register.setComponent("patient/Register");
        register.setChildren(null);

//        Menu outpatientDailySettlementAndCheck = new Menu(); //第二个子级菜单
//        outpatientDailySettlementAndCheck.setName("OutpatientDailySettlementAndCheck");
//        outpatientDailySettlementAndCheck.setTitle("门诊日结核对");
//        outpatientDailySettlementAndCheck.setIcon("");
//        outpatientDailySettlementAndCheck.setPath("/outpatientFinancialManagement/outpatientDailySettlementAndCheck");
//        outpatientDailySettlementAndCheck.setComponent("outpatientFinancialManagement/OutpatientDailySettlementAndCheck");
//        outpatientDailySettlementAndCheck.setChildren(null);
//
//        Menu workloadStatisticsOfOutpatientDepartments = new Menu(); //第三个子级菜单
//        workloadStatisticsOfOutpatientDepartments.setName("WorkloadStatisticsOfOutpatientDepartments");
//        workloadStatisticsOfOutpatientDepartments.setTitle("门诊科室工作量统计");
//        workloadStatisticsOfOutpatientDepartments.setIcon("");
//        workloadStatisticsOfOutpatientDepartments.setPath("/outpatientFinancialManagement/workloadStatisticsOfOutpatientDepartments");
//        workloadStatisticsOfOutpatientDepartments.setComponent("outpatientFinancialManagement/WorkloadStatisticsOfOutpatientDepartments");
//        workloadStatisticsOfOutpatientDepartments.setChildren(null);
//
//        Menu workloadStatisticsOfOutpatientDoctors = new Menu(); //第四个子级菜单
//        workloadStatisticsOfOutpatientDoctors.setName("WorkloadStatisticsOfOutpatientDoctors");
//        workloadStatisticsOfOutpatientDoctors.setTitle("门诊医生工作量统计");
//        workloadStatisticsOfOutpatientDoctors.setIcon("");
//        workloadStatisticsOfOutpatientDoctors.setPath("/outpatientFinancialManagement/workloadStatisticsOfOutpatientDoctors");
//        workloadStatisticsOfOutpatientDoctors.setComponent("outpatientFinancialManagement/WorkloadStatisticsOfOutpatientDoctors");
//        workloadStatisticsOfOutpatientDoctors.setChildren(null);

        Menu patient = new Menu(); //第一个父级菜单
        patient.setName("Patient");
        patient.setTitle("患者就医服务");
        patient.setIcon("");
        patient.setPath("");
        patient.setComponent("");

        List<Menu> allChildren = new ArrayList<>();
        allChildren.add(register);
//        allChildren.add(outpatientDailySettlementAndCheck);
//        allChildren.add(workloadStatisticsOfOutpatientDepartments);
//        allChildren.add(workloadStatisticsOfOutpatientDoctors);
        patient.setChildren(allChildren);

        patientMenuList.add(patient);

        return patientMenuList;
    }

    public List<Menu> outpatientFinancialManagementMenu() { //门诊财务的菜单
        List<Menu> outpatientFinancialManagementMenuList = new ArrayList<>(); //门诊财务的总菜单

        Menu expenseAccountManagement = new Menu(); //第一个子级菜单
        expenseAccountManagement.setName("ExpenseAccountManagement");
        expenseAccountManagement.setTitle("费用科目管理");
        expenseAccountManagement.setIcon("");
        expenseAccountManagement.setPath("/outpatientFinancialManagement/expenseAccountManagement");
        expenseAccountManagement.setComponent("outpatientFinancialManagement/ExpenseAccountManagement");
        expenseAccountManagement.setChildren(null);

//        Menu outpatientDailySettlementAndCheck = new Menu(); //第二个子级菜单
//        outpatientDailySettlementAndCheck.setName("OutpatientDailySettlementAndCheck");
//        outpatientDailySettlementAndCheck.setTitle("门诊日结核对");
//        outpatientDailySettlementAndCheck.setIcon("");
//        outpatientDailySettlementAndCheck.setPath("/outpatientFinancialManagement/outpatientDailySettlementAndCheck");
//        outpatientDailySettlementAndCheck.setComponent("outpatientFinancialManagement/OutpatientDailySettlementAndCheck");
//        outpatientDailySettlementAndCheck.setChildren(null);

        Menu workloadStatisticsOfOutpatientDepartments = new Menu(); //第三个子级菜单
        workloadStatisticsOfOutpatientDepartments.setName("WorkloadStatisticsOfOutpatientDepartments");
        workloadStatisticsOfOutpatientDepartments.setTitle("门诊科室工作量统计");
        workloadStatisticsOfOutpatientDepartments.setIcon("");
        workloadStatisticsOfOutpatientDepartments.setPath("/outpatientFinancialManagement/workloadStatisticsOfOutpatientDepartments");
        workloadStatisticsOfOutpatientDepartments.setComponent("outpatientFinancialManagement/WorkloadStatisticsOfOutpatientDepartments");
        workloadStatisticsOfOutpatientDepartments.setChildren(null);

//        Menu workloadStatisticsOfOutpatientDoctors = new Menu(); //第四个子级菜单
//        workloadStatisticsOfOutpatientDoctors.setName("WorkloadStatisticsOfOutpatientDoctors");
//        workloadStatisticsOfOutpatientDoctors.setTitle("门诊医生工作量统计");
//        workloadStatisticsOfOutpatientDoctors.setIcon("");
//        workloadStatisticsOfOutpatientDoctors.setPath("/outpatientFinancialManagement/workloadStatisticsOfOutpatientDoctors");
//        workloadStatisticsOfOutpatientDoctors.setComponent("outpatientFinancialManagement/WorkloadStatisticsOfOutpatientDoctors");
//        workloadStatisticsOfOutpatientDoctors.setChildren(null);

        Menu outpatientFinancialManagement = new Menu(); //第一个父级菜单
        outpatientFinancialManagement.setName("OutpatientFinancialManagement");
        outpatientFinancialManagement.setTitle("门诊财务管理");
        outpatientFinancialManagement.setIcon("");
        outpatientFinancialManagement.setPath("");
        outpatientFinancialManagement.setComponent("");

        List<Menu> allChildren = new ArrayList<>();
        allChildren.add(expenseAccountManagement);
//        allChildren.add(outpatientDailySettlementAndCheck);
        allChildren.add(workloadStatisticsOfOutpatientDepartments);
//        allChildren.add(workloadStatisticsOfOutpatientDoctors);
        outpatientFinancialManagement.setChildren(allChildren);

        outpatientFinancialManagementMenuList.add(outpatientFinancialManagement);

        return outpatientFinancialManagementMenuList;
    }


    @PostMapping("/sys/menu/nav")
    public ResultUtil menus(@RequestBody User user) {
        System.out.println("前端的用户：" + user);
        if (user.getID() != null) {
            int type = user.getUseType();
            System.out.println("用户类型：" + type);
            switch (type) {
                case 1:
                    return new ResultUtil(EnumCode.OK.getValue(), "生成菜单成功", this.adminMenu());
                case 2:
                    return new ResultUtil(EnumCode.OK.getValue(), "生成菜单成功", this.registeredTollCollectorMenu());
                case 3:
                    return new ResultUtil(EnumCode.OK.getValue(), "生成菜单成功", this.outpatientDoctorWorkstationMenu());
                case 4:
                    return new ResultUtil(EnumCode.OK.getValue(), "生成菜单成功", this.outpatientMedicalTechnologyWorkstationMenu());
                case 5:
                    return new ResultUtil(EnumCode.OK.getValue(), "生成菜单成功", this.outpatientPharmacyWorkstationMenu());
                case 6:
                    return new ResultUtil(EnumCode.OK.getValue(), "生成菜单成功", this.outpatientFinancialManagementMenu());
                case 7:
                    return new ResultUtil(EnumCode.OK.getValue(), "生成菜单成功", this.patientMenu());
                default:
                    return new ResultUtil(EnumCode.LOGIN_FAIL.getValue(), "生成菜单失败", null);

            }

        } else {
            return new ResultUtil(EnumCode.LOGIN_FAIL.getValue(), "生成菜单失败", null);
        }

    }
}

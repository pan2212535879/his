package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.*;

import java.util.List;
import java.util.Map;

public interface PatentPrescriptionService {
    List<Register> initPatients(User user);
    List<Register> searchByName(Register user);
    List<Register> searchByNumber(Register user);
    Integer findMedicalID(MedicalRecord medicalRecord);
    Integer findRegistID(MedicalRecord medicalRecord);
    List<Prescription> initPrescription(Integer medicalID, Integer registID);
    Prescription prescriptionIsExist(Prescription prescription);
    void insertPrescription(Prescription prescription);
    void updatePrescription(Prescription prescription);
    void deletePrescription(Prescription prescription);
    Integer getMedicalID(MedicalRecord medicalRecord);
    List<PrescriptionDetailed> initDetail(Prescription prescription);
    void addDrugs(PrescriptionDetailed prescriptionDetailed);
    void deleteDrugs(PrescriptionDetailed prescriptionDetailed);
    List<Map<Integer, String>> initDrugsPrepare();
    String initName(Integer integer);
    List<DrugsTemplate> getDrugsTemplatePersonally(User user);
    List<DrugsTemplate> getDrugsTemplateByDepartment(User user);
    List<DrugsTemplate> getDrugsTemplateByHospital();
    List<DrugsDetailed> chooseTemplate(DrugsTemplate drugsTemplate);
    void deleteTemplate(DrugsTemplate drugsTemplate);
    void addTemplateConfirm(DrugsTemplate drugsTemplate);
    void deleteTemplateDetail(DrugsDetailed drugsDetailed);
    void addTemplateDetailConfirm(DrugsDetailed drugsDetailed);
}

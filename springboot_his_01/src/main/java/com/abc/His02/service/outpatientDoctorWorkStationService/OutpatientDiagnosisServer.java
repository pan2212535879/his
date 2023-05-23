package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.*;
import com.abc.His02.entity.diagnosisTemplate.DiagnosisTemplate;
import java.util.List;

public interface OutpatientDiagnosisServer {
    List<Register> searchByName(Register register);
    List<Register> searchByNumber(Register register);
    List<Register> initPatients(User user);
    MedicalRecord getRecord(MedicalRecord medicalRecord);
    List<CheckApply> getCheckResult(MedicalRecord medicalRecord);
    List<CheckApply> getDealResult(MedicalRecord medicalRecord);
    List<DiagnosisTemplate> getDiagnosisTemplatePersonally(User user);
    List<DiagnosisTemplate> getDiagnosisTemplateByDepartment(User user);
    List<DiagnosisTemplate> getDiagnosisTemplateByHospital();
    void deleteDiagnosisTemplate(DiagnosisTemplate diagnosisTemplate);
    void commitTemplate(DiagnosisTemplate diagnosisTemplate);
    void confirm(MedicalRecord medicalRecord);
    MedicalRecord recordIsExist(Register register);
    List<DiseCategory> diseaseCategory();
    List<String> initDiseasePrepare();
    List<String> initCategoryDiseasePrepare();
}

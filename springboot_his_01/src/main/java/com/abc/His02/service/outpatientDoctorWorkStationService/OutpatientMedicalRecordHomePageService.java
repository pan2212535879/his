package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.entity.recordTemplate.RecordTemplate;

import java.util.List;

public interface OutpatientMedicalRecordHomePageService {
    List<Register> initPatients(User user);
    List<Register> initHistoryPatients(User user);
    List<Register> searchByName(Register user);
    List<Register> searchByNumber(Register user);
    void saveRecord(MedicalRecord user);
    MedicalRecord recordIsExist(MedicalRecord user);
    void insertRecord(MedicalRecord user);
    void updateRecord(MedicalRecord user);
    void updateRegister(MedicalRecord medicalRecord);
    MedicalRecord getRecord(MedicalRecord user);
    List<RecordTemplate> getRecordTemplatePersonally(User user);
    List<RecordTemplate> getRecordTemplateByDepartment(User user);
    List<RecordTemplate> getRecordTemplateByHospital();
    void deleteRecordTemplate(RecordTemplate record);
    void commitTemplate(RecordTemplate record);
    String searchIDNumber(Register register);
    List<Register> searchRegister(String s);
    MedicalRecord searchHostory(Register register);
    String searchDeptName(Register register);
    MedicalRecord historyRegisterDetail(Register register);
}

package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;

import java.util.List;

public interface AfterDiagnosisService {
    List<Register> initPatients(User user);
    List<Register> searchByName(Register register);
    List<Register> searchByNumber(Register register);
    MedicalRecord getRecord(MedicalRecord medicalRecord);
    void commitEndPatient(Register register);
    void commitEndMedicalRecord(MedicalRecord medicalRecord);
}

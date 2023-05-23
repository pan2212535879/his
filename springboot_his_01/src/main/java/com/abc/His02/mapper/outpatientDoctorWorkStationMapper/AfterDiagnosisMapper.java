package com.abc.His02.mapper.outpatientDoctorWorkStationMapper;

import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface AfterDiagnosisMapper {
    List<Register> initPatients(User user);
    List<Register> searchByName(Register register);
    List<Register> searchByNumber(Register register);
    MedicalRecord getRecord(MedicalRecord medicalRecord);
    void commitEndPatient(Register register);
    void commitEndMedicalRecord(MedicalRecord medicalRecord);
}

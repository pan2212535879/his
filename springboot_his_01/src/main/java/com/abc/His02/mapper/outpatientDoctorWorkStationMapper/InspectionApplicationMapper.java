package com.abc.His02.mapper.outpatientDoctorWorkStationMapper;

import com.abc.His02.entity.CheckApply;
import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InspectionApplicationMapper {
    List<Register> initPatients(User user);
    MedicalRecord initRecords(String s);
    List<CheckApply> applyIsExist(MedicalRecord medicalRecord);
    List<CheckApply> getApply(MedicalRecord medicalRecord);
    CheckApply checkApplyIsExist(CheckApply checkApply);
    void insertCheckApply(CheckApply checkApply);
    void updateCheckApply(CheckApply checkApply);
    void deleteCheckApply(CheckApply checkApply);
    void cancelApply(CheckApply checkApply);
    List<?> initItemName();
    void addApply(CheckApply checkApply);
}

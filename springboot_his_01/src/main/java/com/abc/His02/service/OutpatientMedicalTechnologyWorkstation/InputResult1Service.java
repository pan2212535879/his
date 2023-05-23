package com.abc.His02.service.OutpatientMedicalTechnologyWorkstation;

import com.abc.His02.entity.CheckApply;
import com.abc.His02.entity.MedicalRecord;

import java.util.List;

public interface InputResult1Service {
    List<CheckApply> selectAllCheckApply(CheckApply checkApply);
    CheckApply selectByCheckID(int RegistID);
    int updateCheckApply(CheckApply checkApply);
    MedicalRecord selectByMedicalID(int ID);
    int updateMedicalRecord(MedicalRecord medicalRecord);
}

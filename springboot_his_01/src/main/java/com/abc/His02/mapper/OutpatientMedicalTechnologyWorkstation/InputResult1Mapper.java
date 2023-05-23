package com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.CheckApply;
import com.abc.His02.entity.MedicalRecord;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InputResult1Mapper {
    List<CheckApply> selectAllCheckApply(CheckApply checkApply);
    CheckApply selectByCheckID(int RegistID);
    int updateCheckApply(CheckApply checkApply);

    MedicalRecord selectByMedicalID(int ID);
    int updateMedicalRecord(MedicalRecord medicalRecord);
}

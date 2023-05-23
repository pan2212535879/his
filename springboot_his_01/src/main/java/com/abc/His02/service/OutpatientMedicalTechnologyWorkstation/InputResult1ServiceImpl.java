package com.abc.His02.service.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.CheckApply;
import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation.InputResult1Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InputResult1ServiceImpl implements InputResult1Service {
    @Autowired
    private InputResult1Mapper dao;

    @Override
    public List<CheckApply> selectAllCheckApply(CheckApply checkApply) {
        return dao.selectAllCheckApply(checkApply);
    }

    @Override
    public CheckApply selectByCheckID(int RegistID) {
        return dao.selectByCheckID(RegistID);
    }

    @Override
    public int updateCheckApply(CheckApply checkApply) {
        return dao.updateCheckApply(checkApply);
    }

    @Override
    public MedicalRecord selectByMedicalID(int ID) {
        return dao.selectByMedicalID(ID);
    }

    @Override
    public int updateMedicalRecord(MedicalRecord medicalRecord) {
        return dao.updateMedicalRecord(medicalRecord);
    }


}

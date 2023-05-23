package com.abc.His02.service.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.CheckApply;
import com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation.InspectionAndTestRegisterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class InspectionAndTestRegisterServiceImpl implements InspectionAndTestRegisterService{
    @Autowired
    InspectionAndTestRegisterMapper inspectionAndTestRegisterMapper;

    @Override
    public List<CheckApply> selectAll(CheckApply CheckApply) {
        return inspectionAndTestRegisterMapper.selectAll(CheckApply);
    }

    @Override
    public int updateState(CheckApply CheckAppl) {
        return inspectionAndTestRegisterMapper.updateState(CheckAppl);
    }
}
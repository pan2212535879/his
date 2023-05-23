package com.abc.His02.service.OutpatientMedicalTechnologyWorkstation;

import com.abc.His02.entity.CheckApply;

import java.util.List;

public interface InspectionAndTestRegisterService {
    List<CheckApply> selectAll(CheckApply CheckApply);
    int updateState(CheckApply CheckAppl);
}

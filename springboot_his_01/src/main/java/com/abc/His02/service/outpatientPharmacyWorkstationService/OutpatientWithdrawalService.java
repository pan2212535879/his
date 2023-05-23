package com.abc.His02.service.outpatientPharmacyWorkstationService;

import com.abc.His02.entity.Prescription;

import java.util.List;

public interface OutpatientWithdrawalService {
    List<Prescription> selectAll(Prescription u);
    int update(String u, String Amount);
    int delete(Prescription u);
    Prescription updateAll(String caseNumber);
    Prescription count(String caseNumber);
}

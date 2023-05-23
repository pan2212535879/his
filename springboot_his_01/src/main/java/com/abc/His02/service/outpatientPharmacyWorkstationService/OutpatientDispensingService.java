package com.abc.His02.service.outpatientPharmacyWorkstationService;

import com.abc.His02.entity.Prescription;

import java.util.List;

public interface OutpatientDispensingService {
    List<Prescription> selectAll(Prescription u);
    int update(String u);
}

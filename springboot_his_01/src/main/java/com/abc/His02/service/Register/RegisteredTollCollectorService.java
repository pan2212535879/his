package com.abc.His02.service.Register;

import com.abc.His02.entity.PatientCosts;

import java.util.List;

public interface RegisteredTollCollectorService {
    List<PatientCosts> selectAll(PatientCosts u);
}

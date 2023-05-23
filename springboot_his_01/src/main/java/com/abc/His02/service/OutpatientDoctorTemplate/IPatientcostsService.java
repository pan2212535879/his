package com.abc.His02.service.OutpatientDoctorTemplate;

import com.abc.His02.entity.PatientCosts;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 */
public interface IPatientcostsService {
    List<PatientCosts> selectAll(PatientCosts patientCosts);
    PatientCosts selectById(PatientCosts patientCosts);
    int add(PatientCosts patientCosts);
    int update(PatientCosts patientCosts);
    int delete(PatientCosts patientCosts);
}

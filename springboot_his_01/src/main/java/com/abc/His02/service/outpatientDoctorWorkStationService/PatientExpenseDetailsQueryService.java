package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;

import java.util.List;

public interface PatientExpenseDetailsQueryService {
    List<Register> initPatients(User user);
    List<Register> searchByName(Register register);
    List<Register> searchByNumber(Register register);
    List<PatientCosts> choosePatient(Register register);
}

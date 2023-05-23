package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.mapper.outpatientDoctorWorkStationMapper.PatientExpenseDetailsQueryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PatientExpenseDetailsQueryServiceImpl implements PatientExpenseDetailsQueryService{

    @Autowired
    private PatientExpenseDetailsQueryMapper dao;

    @Override
    public List<Register> initPatients(User user) { return dao.initPatients(user); }

    @Override
    public List<Register> searchByName(Register register) { return dao.searchByName(register); }

    @Override
    public List<Register> searchByNumber(Register register) { return dao.searchByNumber(register); }

    @Override
    public List<PatientCosts> choosePatient(Register register) { return dao.choosePatient(register); }
}

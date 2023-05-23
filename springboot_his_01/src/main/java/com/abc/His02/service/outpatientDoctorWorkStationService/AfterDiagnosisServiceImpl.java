package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.mapper.outpatientDoctorWorkStationMapper.AfterDiagnosisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class AfterDiagnosisServiceImpl implements AfterDiagnosisService {
    @Autowired
    private AfterDiagnosisMapper dao;

    @Override
    public List<Register> initPatients(User user) { return dao.initPatients(user); }

    @Override
    public List<Register> searchByName(Register register) { return dao.searchByName(register); }

    @Override
    public List<Register> searchByNumber(Register register) { return dao.searchByNumber(register); }

    @Override
    public MedicalRecord getRecord(MedicalRecord medicalRecord) { return dao.getRecord(medicalRecord); }

    @Override
    public void commitEndPatient(Register register) { dao.commitEndPatient(register); }

    @Override
    public void commitEndMedicalRecord(MedicalRecord medicalRecord) { dao.commitEndMedicalRecord(medicalRecord); }
}

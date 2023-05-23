package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.CheckApply;
import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.mapper.outpatientDoctorWorkStationMapper.DisposalApplicationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DisposalApplicationServiceImpl implements DisposalApplicationService{
    @Autowired
    private DisposalApplicationMapper dao;

    @Override
    public List<Register> initPatients(User user) {
        return dao.initPatients(user);
    }

    @Override
    public MedicalRecord initRecords(String s) { return dao.initRecords(s); }

    @Override
    public List<CheckApply> applyIsExist(MedicalRecord medicalRecord) { return dao.applyIsExist(medicalRecord); }

    @Override
    public List<CheckApply> getApply(MedicalRecord medicalRecord) { return dao.getApply(medicalRecord); }

    @Override
    public CheckApply checkApplyIsExist(CheckApply checkApply) { return dao.checkApplyIsExist(checkApply); }

    @Override
    public void insertCheckApply(CheckApply checkApply) { dao.insertCheckApply(checkApply); }

    @Override
    public void updateCheckApply(CheckApply checkApply) { dao.updateCheckApply(checkApply); }

    @Override
    public void deleteCheckApply(CheckApply checkApply) { dao.deleteCheckApply(checkApply); }

    @Override
    public List<?> initItemName() { return dao.initItemName(); }

    @Override
    public void addApply(CheckApply checkApply) { dao.addApply(checkApply); }
}

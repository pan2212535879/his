package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.MedicalRecord;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import com.abc.His02.entity.recordTemplate.RecordTemplate;
import com.abc.His02.mapper.outpatientDoctorWorkStationMapper.OutpatientMedicalRecordHomePageMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OutpatientMedicalRecordHomePageServiceImpl implements OutpatientMedicalRecordHomePageService {
    @Autowired
    private
    OutpatientMedicalRecordHomePageMapper dao;

    @Override
    public List<Register> initPatients(User user) {
        return dao.initPatients(user);
    }

    @Override
    public List<Register> initHistoryPatients(User user) {
        return dao.initHistoryPatients(user);
    }

    @Override
    public List<Register> searchByName(Register user) {
        return dao.searchByName(user);
    }

    @Override
    public List<Register> searchByNumber(Register user) {
        return dao.searchByNumber(user);
    }

    @Override
    public void saveRecord(MedicalRecord user) {  dao.saveRecord(user); }

    @Override
    public MedicalRecord recordIsExist(MedicalRecord user) { return dao.recordIsExist(user); }

    @Override
    public void insertRecord(MedicalRecord user) { dao.insertRecord(user); }

    @Override
    public void updateRecord(MedicalRecord user) { dao.updateRecord(user); }

    @Override
    public void updateRegister(MedicalRecord medicalRecord) { dao.updateRegister(medicalRecord); }

    @Override
    public MedicalRecord getRecord(MedicalRecord user) { return dao.getRecord(user); }

    @Override
    public List<RecordTemplate> getRecordTemplatePersonally(User user) { return dao.getRecordTemplatePersonally(user); }

    @Override
    public List<RecordTemplate> getRecordTemplateByDepartment(User user) { return dao.getRecordTemplateByDepartment(user); }

    @Override
    public List<RecordTemplate> getRecordTemplateByHospital() { return dao.getRecordTemplateByHospital(); }

    @Override
    public void deleteRecordTemplate(RecordTemplate record) { dao.deleteRecordTemplate(record); }

    @Override
    public void commitTemplate(RecordTemplate record) { dao.commitTemplate(record); }

    @Override
    public String searchIDNumber(Register register) { return dao.searchIDNumber(register); }

    @Override
    public List<Register> searchRegister(String s) { return dao.searchRegister(s); }

    @Override
    public MedicalRecord searchHostory(Register register) {
        return dao.searchHostory(register);
    }

    @Override
    public String searchDeptName(Register register) {
        return dao.searchDeptName(register);
    }

    @Override
    public MedicalRecord historyRegisterDetail(Register register) {
        return dao.historyRegisterDetail(register);
    }
}

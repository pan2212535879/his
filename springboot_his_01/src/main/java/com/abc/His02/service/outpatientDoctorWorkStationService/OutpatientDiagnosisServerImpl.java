package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.*;
import com.abc.His02.entity.diagnosisTemplate.DiagnosisTemplate;
import com.abc.His02.mapper.outpatientDoctorWorkStationMapper.OutpatientDiagnosisMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
/**
 * @Transactional: 作用就是对某个东西进行回滚
 * */
@Service
@Transactional
public class OutpatientDiagnosisServerImpl implements OutpatientDiagnosisServer{
    @Autowired
    private OutpatientDiagnosisMapper dao;

    @Override
    public List<Register> searchByName(Register register) { return dao.searchByName(register); }

    @Override
    public List<Register> searchByNumber(Register register) { return dao.searchByNumber(register); }

    @Override
    public List<Register> initPatients(User user) { return dao.initPatients(user); }

    @Override
    public MedicalRecord getRecord(MedicalRecord medicalRecord) { return dao.getRecord(medicalRecord); }

    @Override
    public List<CheckApply> getCheckResult(MedicalRecord medicalRecord) { return dao.getCheckResult(medicalRecord); }

    @Override
    public List<CheckApply> getDealResult(MedicalRecord medicalRecord) { return dao.getDealResult(medicalRecord); }

    @Override
    public List<DiagnosisTemplate> getDiagnosisTemplatePersonally(User user) { return dao.getDiagnosisTemplatePersonally(user); }

    @Override
    public List<DiagnosisTemplate> getDiagnosisTemplateByDepartment(User user) { return dao.getDiagnosisTemplateByDepartment(user); }

    @Override
    public List<DiagnosisTemplate> getDiagnosisTemplateByHospital() { return dao.getDiagnosisTemplateByHospital(); }

    @Override
    public void deleteDiagnosisTemplate(DiagnosisTemplate diagnosisTemplate) {  dao.deleteDiagnosisTemplate(diagnosisTemplate); }

    @Override
    public void commitTemplate(DiagnosisTemplate diagnosisTemplate) { dao.commitTemplate(diagnosisTemplate); }

    @Override
    public void confirm(MedicalRecord medicalRecord) { dao.confirm(medicalRecord); }

    @Override
    public MedicalRecord recordIsExist(Register register) { return dao.recordIsExist(register); }

    @Override
    public List<DiseCategory> diseaseCategory() { return dao.diseaseCategory(); }

    @Override
    public List<String> initDiseasePrepare() { return dao.initDiseasePrepare(); }

    @Override
    public List<String> initCategoryDiseasePrepare() { return dao.initCategoryDiseasePrepare(); }

}

package com.abc.His02.service.outpatientDoctorWorkStationService;

import com.abc.His02.entity.*;
import com.abc.His02.mapper.outpatientDoctorWorkStationMapper.PatentPrescriptionMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class PatentPrescriptionServiceImpl implements PatentPrescriptionService{
    @Autowired
    private PatentPrescriptionMapper dao;

    @Override
    public List<Register> initPatients(User user) { return dao.initPatients(user); }

    @Override
    public List<Register> searchByName(Register user) { return dao.searchByName(user); }

    @Override
    public List<Register> searchByNumber(Register user) { return dao.searchByNumber(user); }

    @Override
    public Integer findMedicalID(MedicalRecord medicalRecord) { return dao.findMedicalID(medicalRecord); }

    @Override
    public Integer findRegistID(MedicalRecord medicalRecord) { return dao.findRegistID(medicalRecord); }

    @Override
    public List<Prescription> initPrescription(Integer medicalID, Integer registID) { return dao.initPrescription(medicalID, registID); }

    @Override
    public Prescription prescriptionIsExist(Prescription prescription) { return dao.prescriptionIsExist(prescription); }

    @Override
    public void insertPrescription(Prescription prescription) { dao.insertPrescription(prescription); }

    @Override
    public void updatePrescription(Prescription prescription) { dao.updatePrescription(prescription); }

    @Override
    public void deletePrescription(Prescription prescription) { dao.deletePrescription(prescription); }

    @Override
    public Integer getMedicalID(MedicalRecord medicalRecord) { return dao.getMedicalID(medicalRecord); }

    @Override
    public List<PrescriptionDetailed> initDetail(Prescription prescription) { return dao.initDetail(prescription); }

    @Override
    public void addDrugs(PrescriptionDetailed prescriptionDetailed) { dao.addDrugs(prescriptionDetailed); }

    @Override
    public void deleteDrugs(PrescriptionDetailed prescriptionDetailed) { dao.deleteDrugs(prescriptionDetailed); }

    @Override
    public List<Map<Integer, String>> initDrugsPrepare() { return dao.initDrugsPrepare(); }

    @Override
    public String initName(Integer integer) { return dao.initName(integer); }

    @Override
    public List<DrugsTemplate> getDrugsTemplatePersonally(User user) { return dao.getDrugsTemplatePersonally(user); }

    @Override
    public List<DrugsTemplate> getDrugsTemplateByDepartment(User user) { return dao.getDrugsTemplateByDepartment(user); }

    @Override
    public List<DrugsTemplate> getDrugsTemplateByHospital() { return dao.getDrugsTemplateByHospital(); }

    @Override
    public List<DrugsDetailed> chooseTemplate(DrugsTemplate drugsTemplate) { return dao.chooseTemplate(drugsTemplate); }

    @Override
    public void deleteTemplate(DrugsTemplate drugsTemplate) { dao.deleteTemplate(drugsTemplate); }

    @Override
    public void addTemplateConfirm(DrugsTemplate drugsTemplate) { dao.addTemplateConfirm(drugsTemplate); }

    @Override
    public void deleteTemplateDetail(DrugsDetailed drugsDetailed) { dao.deleteTemplateDetail(drugsDetailed); }

    @Override
    public void addTemplateDetailConfirm(DrugsDetailed drugsDetailed) { dao.addTemplateDetailConfirm(drugsDetailed); }
}

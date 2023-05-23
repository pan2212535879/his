package com.abc.His02.service.OutpatientDoctorTemplate;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.mapper.OutpatientDoctorTemplate.PatientcostsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
@Transactional
public class PatientcostsServiceImpl implements IPatientcostsService {

    @Autowired
    PatientcostsMapper dao;

    @Override
    public List<PatientCosts> selectAll(PatientCosts patientCosts) {
        return dao.selectAll(patientCosts);
    }

    @Override
    public PatientCosts selectById(PatientCosts patientCosts) {
        return dao.selectById(patientCosts);
    }

    @Override
    public int add(PatientCosts patientCosts) {
        return dao.add(patientCosts);
    }

    @Override
    public int update(PatientCosts patientCosts) {
        return dao.update(patientCosts);
    }

    @Override
    public int delete(PatientCosts patientCosts) {
        return dao.delete(patientCosts);
    }
}

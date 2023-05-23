package com.abc.His02.service.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.CheckApply;
import com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation.PersonalWorkloadStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonalWorkloadStatisticsServiceImpl implements PersonalWorkloadStatisticsService{
    @Autowired
    private PersonalWorkloadStatisticsMapper dao;

    @Override
    public List<CheckApply> selectAll(CheckApply u) {

        return dao.selectAll(u);
    }

    @Override
    public CheckApply selectByID() {

        return dao.selectByID();
    }
}

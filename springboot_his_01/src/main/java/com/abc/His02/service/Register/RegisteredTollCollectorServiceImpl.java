package com.abc.His02.service.Register;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.mapper.Register.RegisteredTollCollectorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RegisteredTollCollectorServiceImpl implements RegisteredTollCollectorService {

    @Autowired
    RegisteredTollCollectorMapper dao;

    @Override
    public List<PatientCosts> selectAll(PatientCosts u) {

        return dao.selectAll(u);
    }

}

package com.abc.His02.service.OutpatientDoctorTemplate;

import com.abc.His02.entity.Register;
import com.abc.His02.mapper.OutpatientDoctorTemplate.Register2Mapper;
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
public class Register2ServiceImpl implements IRegisterService {

    @Autowired
    Register2Mapper dao;

    @Override
    public List<Register> selectAll(Register register) {
        return dao.selectAll(register);
    }

    @Override
    public Register selectById(Register register) {
        return dao.selectById(register);
    }

    @Override
    public int add(Register register) {
        return dao.add(register);
    }

    @Override
    public int update(Register register) {
        return dao.update(register);
    }

    @Override
    public int delete(Register register) {
        return dao.delete(register);
    }
}

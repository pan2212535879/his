package com.abc.His02.service.OutpatientDoctorTemplate;

import com.abc.His02.entity.Register;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 */
public interface IRegisterService{
    List<Register> selectAll(Register register);
    Register selectById(Register register);
    int add(Register register);
    int update(Register register);
    int delete(Register register);
}

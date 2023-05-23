package com.abc.His02.mapper.outpatientDoctorWorkStationMapper;

import com.abc.His02.entity.PatientCosts;
import com.abc.His02.entity.Register;
import com.abc.His02.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PatientExpenseDetailsQueryMapper {
    List<Register> initPatients(User user);
    List<Register> searchByName(Register register);
    List<Register> searchByNumber(Register register);
    List<PatientCosts> choosePatient(Register register);
}

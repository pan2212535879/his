package com.abc.His02.mapper.Register;

import com.abc.His02.entity.PatientCosts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RegisteredTollCollectorMapper {

    List<PatientCosts> selectAll(PatientCosts u);
}

package com.abc.His02.mapper.OutpatientDoctorTemplate;

import com.abc.His02.entity.PatientCosts;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface PatientcostsMapper{
    List<PatientCosts> selectAll(PatientCosts patientCosts);
    PatientCosts selectById(PatientCosts patientCosts);
    int add(PatientCosts patientCosts);
    int update(PatientCosts patientCosts);
    int delete(PatientCosts patientCosts);
}

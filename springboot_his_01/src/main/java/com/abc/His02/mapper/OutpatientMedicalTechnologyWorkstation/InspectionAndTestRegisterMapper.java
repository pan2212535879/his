package com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.CheckApply;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InspectionAndTestRegisterMapper {
    List<CheckApply> selectAll(CheckApply CheckApply);
    int updateState(CheckApply CheckAppl);
}

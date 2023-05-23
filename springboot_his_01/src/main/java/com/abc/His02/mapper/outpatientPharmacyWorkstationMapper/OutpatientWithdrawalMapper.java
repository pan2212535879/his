package com.abc.His02.mapper.outpatientPharmacyWorkstationMapper;

import com.abc.His02.entity.Prescription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutpatientWithdrawalMapper {
    List<Prescription> selectAll(Prescription u);
    int add(Prescription u);
    int update(String id, String amount);
    int delete(Prescription u);
    int updateAll(String caseNumber);
    Prescription count(String caseNumber);
}

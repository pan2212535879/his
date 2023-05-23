package com.abc.His02.mapper.outpatientPharmacyWorkstationMapper;

import com.abc.His02.entity.Prescription;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OutpatientDispensingMapper {
    List<Prescription> selectAll(Prescription u);
    // public int add(Prescription u);
    int update(String id);
    // public int delete(Prescription u);
    // public Prescription selectById(Prescription u);
}

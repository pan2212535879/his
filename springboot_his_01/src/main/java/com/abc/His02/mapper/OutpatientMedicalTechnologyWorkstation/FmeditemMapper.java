package com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.Fmeditem;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface FmeditemMapper {
    List<Fmeditem> selectAll(Fmeditem fmeditem);
}

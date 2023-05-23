package com.abc.His02.mapper.outpatientPharmacyWorkstationMapper;

import com.abc.His02.entity.Drugs;
import org.apache.ibatis.annotations.Mapper;

import java.math.BigDecimal;
import java.util.List;

@Mapper
public interface DrugCatalogueManagementMapper {
    List<Drugs> selectAll(Drugs u);
    int add(Drugs u);
    int update(Integer ID, String DrugsCode, String DrugsName, String DrugsFormat, String DrugsUnit, String Manufacturer, Integer DrugsDosageID, Integer DrugsTypeID, BigDecimal DrugsPrice, String MnemonicCode);
    int delete(Integer id);
    Drugs selectById(Drugs u);
}

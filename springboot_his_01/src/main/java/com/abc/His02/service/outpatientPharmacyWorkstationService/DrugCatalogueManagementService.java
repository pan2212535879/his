package com.abc.His02.service.outpatientPharmacyWorkstationService;

import com.abc.His02.entity.Drugs;

import java.math.BigDecimal;
import java.util.List;

public interface DrugCatalogueManagementService {
    List<Drugs> selectAll(Drugs u);
    Drugs selectById(Drugs u);
    int add(Drugs u);
    int update(Integer ID, String DrugsCode, String DrugsName, String DrugsFormat, String DrugsUnit, String Manufacturer, Integer DrugsDosageID, Integer DrugsTypeID, BigDecimal DrugsPrice, String MnemonicCode);
    int delete(Integer id);
}

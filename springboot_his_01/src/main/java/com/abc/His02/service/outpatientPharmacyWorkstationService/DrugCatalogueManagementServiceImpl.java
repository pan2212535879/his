package com.abc.His02.service.outpatientPharmacyWorkstationService;

import com.abc.His02.entity.Drugs;
import com.abc.His02.mapper.outpatientPharmacyWorkstationMapper.DrugCatalogueManagementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
@Transactional
public class DrugCatalogueManagementServiceImpl implements DrugCatalogueManagementService{
    @Autowired
    private DrugCatalogueManagementMapper dao;

    @Override
    public List<Drugs> selectAll(Drugs u) {
        return dao.selectAll(u);
    }

    @Override
    public Drugs selectById(Drugs u) {
        return null;
    }

    @Override
    public int add(Drugs u) {
        return dao.add(u);
    }


    @Override
    public int update(Integer ID, String DrugsCode, String DrugsName, String DrugsFormat, String DrugsUnit, String Manufacturer, Integer DrugsDosageID, Integer DrugsTypeID, BigDecimal DrugsPrice, String MnemonicCode) {
        return dao.update(ID,DrugsCode,DrugsName,DrugsFormat,DrugsUnit,Manufacturer,DrugsDosageID,DrugsTypeID,DrugsPrice,MnemonicCode);
    }

    @Override
    public int delete(Integer id) {
        System.out.println(id);

        return dao.delete(id);
    }
}

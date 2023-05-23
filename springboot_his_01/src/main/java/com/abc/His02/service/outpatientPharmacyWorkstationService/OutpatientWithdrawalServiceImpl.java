package com.abc.His02.service.outpatientPharmacyWorkstationService;

import com.abc.His02.entity.Prescription;
import com.abc.His02.mapper.outpatientPharmacyWorkstationMapper.OutpatientWithdrawalMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OutpatientWithdrawalServiceImpl implements OutpatientWithdrawalService{

    @Autowired
    private OutpatientWithdrawalMapper dao;

    @Override
    public List<Prescription> selectAll(Prescription u) {

        return dao.selectAll(u);
    }


    @Override
    public int update(String id,String amount) {

        return dao.update(id,amount);
    }

    @Override
    public int delete(Prescription u) {

        return dao.delete(u);
    }

    @Override
    public Prescription updateAll(String caseNumber) {
        Prescription res = dao.count(caseNumber);
        dao.updateAll(caseNumber);
        return  res;
    }

    @Override
    public Prescription count(String caseNumber) {
        return dao.count(caseNumber);
    }


}

package com.abc.His02.service.outpatientPharmacyWorkstationService;

import com.abc.His02.entity.Prescription;
import com.abc.His02.mapper.outpatientPharmacyWorkstationMapper.OutpatientDispensingMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OutpatientDispensingServiceImpl implements OutpatientDispensingService{
    @Autowired
    private OutpatientDispensingMapper dao;

    @Override
    public List<Prescription> selectAll(Prescription u) {

        return dao.selectAll(u);
    }



    @Override
    public int update(String id) {

        return dao.update(id);
    }


}

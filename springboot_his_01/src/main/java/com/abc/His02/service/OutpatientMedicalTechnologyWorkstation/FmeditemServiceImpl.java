package com.abc.His02.service.OutpatientMedicalTechnologyWorkstation;


import com.abc.His02.entity.Fmeditem;
import com.abc.His02.mapper.OutpatientMedicalTechnologyWorkstation.FmeditemMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FmeditemServiceImpl implements FmeditemService {
    @Autowired
    FmeditemMapper dao;

    @Override
    public List<Fmeditem> selectAll(Fmeditem fmeditem) {
        return dao.selectAll(fmeditem);
    }
}

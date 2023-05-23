package com.abc.His02.service.OutpatientDoctorTemplate;

import com.abc.His02.entity.CheckTemplate;
import com.abc.His02.mapper.OutpatientDoctorTemplate.CheckTemplateMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 */
@Service
@Transactional
public class CheckTemplateServiceImpl implements ICheckTemplateService {

    @Autowired
    CheckTemplateMapper dao;

    @Override
    public List<CheckTemplate> selectAll(CheckTemplate checkTemplate) {
        return dao.selectAll(checkTemplate);
    }

    @Override
    public CheckTemplate selectById(CheckTemplate checkTemplate) {
        return dao.selectById(checkTemplate);
    }

    @Override
    public int add(CheckTemplate checkTemplate) {
        return dao.add(checkTemplate);
    }

    @Override
    public int update(CheckTemplate checkTemplate) {
        return dao.update(checkTemplate);
    }

    @Override
    public int delete(CheckTemplate checkTemplate) {
        return dao.delete(checkTemplate);
    }
}

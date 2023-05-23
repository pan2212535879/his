package com.abc.His02.service.OutpatientDoctorTemplate;

import com.abc.His02.entity.CheckTemplate;

import java.util.List;

public interface ICheckTemplateService {
    List<CheckTemplate> selectAll(CheckTemplate checkTemplate);
    CheckTemplate selectById(CheckTemplate checkTemplate);
    int add(CheckTemplate checkTemplate);
    int update(CheckTemplate checkTemplate);
    int delete(CheckTemplate checkTemplate);
}

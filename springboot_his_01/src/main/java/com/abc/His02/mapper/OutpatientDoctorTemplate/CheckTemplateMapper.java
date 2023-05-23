package com.abc.His02.mapper.OutpatientDoctorTemplate;

import com.abc.His02.entity.CheckTemplate;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface CheckTemplateMapper{
    List<CheckTemplate> selectAll(CheckTemplate checkTemplate);
    CheckTemplate selectById(CheckTemplate checkTemplate);
    int add(CheckTemplate checkTemplate);
    int update(CheckTemplate checkTemplate);
    int delete(CheckTemplate checkTemplate);
}

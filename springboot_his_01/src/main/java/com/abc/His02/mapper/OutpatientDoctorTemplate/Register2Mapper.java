package com.abc.His02.mapper.OutpatientDoctorTemplate;

import com.abc.His02.entity.Register;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 */
@Mapper
public interface Register2Mapper {
    List<Register> selectAll(Register register);
    Register selectById(Register register);
    int add(Register register);
    int update(Register register);
    int delete(Register register);

}

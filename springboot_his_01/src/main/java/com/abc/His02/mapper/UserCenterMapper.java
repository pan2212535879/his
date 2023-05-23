package com.abc.His02.mapper;

import com.abc.His02.entity.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserCenterMapper {
    User passwordIsRight(Integer ID, String password);
    void changePassword(Integer ID, String password);
}

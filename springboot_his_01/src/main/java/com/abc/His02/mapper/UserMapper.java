package com.abc.His02.mapper;

import com.abc.His02.entity.Department;
import com.abc.His02.entity.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {
    List<User> selectAll(User u);
    User selectById(User u);
    int add(User u);
    int update(User u);
    int delete(User u);
    User login(User u);
    Department selectDeptByID(User u);
}

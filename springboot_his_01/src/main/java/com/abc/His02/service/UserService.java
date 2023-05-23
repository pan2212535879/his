package com.abc.His02.service;

import com.abc.His02.entity.Department;
import com.abc.His02.entity.User;

import java.util.List;

public interface UserService {
    List<User> selectAll(User u);
    User selectById(User u);
    int add(User u);
    int update(User u);
    int delete(User u);

    User login(User u);
    Department selectDeptByID(User u);
}

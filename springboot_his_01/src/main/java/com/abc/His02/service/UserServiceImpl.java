package com.abc.His02.service;

import com.abc.His02.entity.Department;
import com.abc.His02.entity.User;
import com.abc.His02.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper dao;

    @Override
    public List<User> selectAll(User u) {
        return dao.selectAll(u);
    }

    @Override
    public User selectById(User u) {
        return dao.selectById(u);
    }

    @Override
    public int add(User u) {
        return dao.add(u);
    }

    @Override
    public int update(User u) {
        return dao.update(u);
    }

    @Override
    public int delete(User u) {
        return dao.delete(u);
    }

    @Override
    public User login(User u) {
        return dao.login(u);
    }

    @Override
    public Department selectDeptByID(User u) {
        return dao.selectDeptByID(u);
    }
}

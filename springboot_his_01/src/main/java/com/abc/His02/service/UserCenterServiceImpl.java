package com.abc.His02.service;

import com.abc.His02.entity.User;
import com.abc.His02.mapper.UserCenterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class UserCenterServiceImpl implements UserCenterService {

    @Autowired
    private UserCenterMapper dao;

    @Override
    public User passwordIsRight(Integer ID, String password) { return dao.passwordIsRight(ID, password); }

    @Override
    public void changePassword(Integer ID, String password) { dao.changePassword(ID, password); }
}

package com.abc.His02.service;

import com.abc.His02.entity.User;
import com.abc.His02.entity.menu.Menu;
import com.abc.His02.mapper.MenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService{
    @Autowired
    private MenuMapper dao;

    @Override
    public List<Menu> selectAll(User u) {
        return dao.selectAll(u);
    }
}

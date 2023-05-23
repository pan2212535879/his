package com.abc.His02.service;

import com.abc.His02.entity.User;
import com.abc.His02.entity.menu.Menu;

import java.util.List;

public interface MenuService {
    List<Menu> selectAll(User u);
}

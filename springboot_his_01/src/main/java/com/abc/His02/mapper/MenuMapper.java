package com.abc.His02.mapper;

import com.abc.His02.entity.User;
import com.abc.His02.entity.menu.Menu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MenuMapper {
    List<Menu> selectAll(User u);
}

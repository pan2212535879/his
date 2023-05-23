package com.abc.His02.entity.menu;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Menu {
    private String name;
    private String title;
    private String icon;
    private String path;
    private String component;
    private List<Menu> children;

}

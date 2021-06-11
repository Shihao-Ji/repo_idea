package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;

import java.util.List;

public interface MenuService {

    /*
        查询所有父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /*
        查询所有菜单信息
     */
    public PageInfo<Menu> findAllMenu(MenuVO menuVO);

    /*
        回显菜单信息
     */
    public Menu findMenuById(int id);
}

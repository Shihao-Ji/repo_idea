package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /*
        查询所有父子菜单信息
     */
    public List<Menu> findSubMenuListByPid(int pid);

    /**
     * 查询菜单列表
     * */
    public List<Menu> findAllMenu();

    /*
        回显菜单信息
     */
    public Menu findMenuById(int id);

    /*
        保存菜单
     */
    public void saveMenu(Menu menu);

    /*
        修改菜单
     */
    public void updateMenu(Menu menu);

    /*
        根据id删除菜单
     */
    public void deleteMenu(int id);
}

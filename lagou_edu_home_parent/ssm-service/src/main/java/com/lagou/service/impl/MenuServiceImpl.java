package com.lagou.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<Menu> findSubMenuListByPid(int pid) {
        List<Menu> subMenuListByPid = menuMapper.findSubMenuListByPid(pid);
        System.out.println(subMenuListByPid);
        return subMenuListByPid;
    }

    @Override
    public PageInfo<Menu> findAllMenu(MenuVO menuVO) {

        // 分页助手进行分页
        PageHelper.startPage(menuVO.getCurrentPage(),menuVO.getPageSize());
        // 查询出已分页的list
        List<Menu> MenuList = menuMapper.findAllMenu();
        // 声明一个PageInfo对象，把list作为参数传入，得到的对象可以获得各种分页数据
        PageInfo<Menu> pageInfo = new PageInfo<>(MenuList);

        return pageInfo;
    }

    @Override
    public Menu findMenuById(int id) {
        return menuMapper.findMenuById(id);
    }

    @Override
    public void saveMenu(Menu menu) {
        // 补全信息
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("system");
        menu.setUpdatedBy("system");
        if (menu.getParentId() != -1){
            menu.setLevel(1);
        } else {
            menu.setLevel(0);
        }

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        // 补全信息
        Date date = new Date();
        menu.setUpdatedTime(date);
        menu.setUpdatedBy("system");
        if (menu.getParentId() != -1){
            menu.setLevel(1);
        } else {
            menu.setLevel(0);
        }

        menuMapper.updateMenu(menu);
    }

    @Override
    public void deleteMenu(int id) {
        menuMapper.deleteMenu(id);
    }

}

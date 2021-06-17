package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
        查询所有的父子菜单信息
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(MenuVO menuVO){
        PageInfo<Menu> pageInfo = menuService.findAllMenu(menuVO);
        return new ResponseResult(true,200,"查询所有父子菜单信息成功",pageInfo);
    }

    /*
        回显菜单信息
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(int id){
        if (id == -1) {
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",null);
            map.put("parentMenuList",subMenuListByPid);
            return new ResponseResult(true,200,"回显新建菜单信息成功",map);
        } else {
            Menu menu = menuService.findMenuById(id);
            List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);
            Map<String, Object> map = new HashMap<>();
            map.put("menuInfo",menu);
            map.put("parentMenuList",subMenuListByPid);
            return new ResponseResult(true,200,"回显修改菜单信息成功",map);
        }
    }

    /*
        新建或修改菜单
     */
    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if (menu.getId() != null){
            menuService.updateMenu(menu);
            return new ResponseResult(true,200,"修改菜单信息成功",null);
        } else {
            menuService.saveMenu(menu);
            return new ResponseResult(true,200,"保存菜单信息成功",null);
        }
    }

    /*
        根据id删除菜单
     */
    @RequestMapping("/deleteMenu")
    public ResponseResult deleteMenu(Integer id){
        menuService.deleteMenu(id);
        return new ResponseResult(true,200,"删除菜单成功",null);
    }

}

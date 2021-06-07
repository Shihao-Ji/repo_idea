package com.lagou.controller;

import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /*
        查询所有角色(条件)
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> roleList = roleService.findAllRole(role);
        return new ResponseResult(true,200,"查询所有角色成功",roleList);
    }

    /*
        根据角色id查询关联的菜单id
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(int roleId){
        List<Integer> menuIdList = roleService.findMenuByRoleId(roleId);
        return new ResponseResult(true,200,"根据角色id查询关联的菜单id成功",menuIdList);
    }

    /*
        为角色赋予新的菜单权限
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){
        roleService.RoleContextMenu(roleMenuVo);
        return new ResponseResult(true,200,"为角色赋予新的菜单权限成功","");
    }

    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(int id){
        roleService.deleteRole(id);
        return new ResponseResult(true,200,"删除角色成功","");
    }

    /*
        获取角色拥有的资源信息
     */
    @RequestMapping("/findResourceListByRoleId")
    public ResponseResult findResourceListByRoleId(int roleId){
        List<Resource> resourceList = roleService.findResourceListByRoleId(roleId);
        return new ResponseResult(true,200,"获取角色拥有的资源信息成功",resourceList);
    }

    /*
        为角色分配菜单
     */
    @RequestMapping("/roleContextResource")
    public ResponseResult roleContextResource(@RequestBody RoleResourceVo roleResourceVo){
        roleService.roleContextResource(roleResourceVo);
        return new ResponseResult(true,200,"为角色分配菜单成功",null);
    }
}

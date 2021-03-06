package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色(条件)
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色ID查询关联的菜单信息ID
     */
    public List<Integer> findMenuByRoleId(int role_id);

    /*
        清空角色菜单中间表的关联关系
     */
    public void deleteRoleContextMenu(int rid);

    /*
        为角色菜单关联表添加新的关联关系
     */
    public void RoleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    public void deleteRole(int id);

    /*
        获取角色拥有的资源分类id列表
     */
    public List<Integer> findResourceCategoryIdsByRoleId(Integer roleId);

    /*
        获取角色拥有的资源分类信息
     */
    public List<ResourceCategory> findResourceCategoryListByIds(List<Integer> ids);

    /*
        获取角色拥有的资源信息
     */
    public List<Resource> findResourceListByRoleId(Integer roleId);

    /*
        根据角色id清空角色菜单关联表关系信息
     */
    public void deleteRoleContextResource(Integer roleId);

    /*
        为角色分配菜单
     */
    public void roleContextResource(Role_Resource_Relation role_resource_relation);

    /*
        添加角色
     */
    public void saveRole(Role role);

    /*
        修改角色
     */
    public void updateRole(Role role);

    /*
        查询所有的父子菜单信息(分配菜单的第一个接口)
     */
    public List<Menu> findAllMenu(Integer id);
}

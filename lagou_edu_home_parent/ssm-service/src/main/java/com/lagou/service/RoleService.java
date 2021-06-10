package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色(条件)
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色ID查询关联的菜单信息ID
     */
    public List<Integer> findMenuByRoleId(int role_id);

    /*
        为角色菜单关联表添加新的关联关系
     */
    public void RoleContextMenu(RoleMenuVo roleMenuVo);

    /*
        删除角色
     */
    public void deleteRole(int rid);

    /*
        获取角色拥有的资源信息
     */
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    /*
        为角色分配菜单
     */
    void roleContextResource(RoleResourceVo roleResourceVo);

    /*
        添加角色
     */
    public void saveRole(Role role);

    /*
        修改角色
     */
    public void updateRole(Role role);

}

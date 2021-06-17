package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public List<Integer> findMenuByRoleId(int role_id) {
        return roleMapper.findMenuByRoleId(role_id);
    }

    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo) {

        roleMapper.deleteRoleContextMenu(roleMenuVo.getRoleId());

        for (Integer mid : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());
            role_menu_relation.setMenuId(mid);
            Date date = new Date();
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");
            System.out.println(role_menu_relation);
            roleMapper.RoleContextMenu(role_menu_relation);
        }
    }

    @Override
    public void deleteRole(int rid) {
        roleMapper.deleteRoleContextMenu(rid);
        roleMapper.deleteRole(rid);
    }

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        // 获取角色拥有的所有资源分类id
        List<Integer> resourceCategoryIds = roleMapper.findResourceCategoryIdsByRoleId(roleId);
        // 获取角色拥有的所有资源分类
        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryListByIds(resourceCategoryIds);
        // 获取角色拥有的所有资源
        List<Resource> resourceList = roleMapper.findResourceListByRoleId(roleId);
        // 遍历资源分类list，然后把资源中分类号匹配的资源放进资源分类的resourceList属性中
        for (ResourceCategory resourceCategory : resourceCategoryList) {
            List<Resource> resourceList2 = new ArrayList<>();
            Integer resourceCategoryId = resourceCategory.getId();
            for (Resource resource : resourceList) {
                if (resource.getCategoryId() == resourceCategoryId) {
                    resourceList2.add(resource);
                }
            }
            resourceCategory.setResourceList(resourceList2);
        }
        return resourceCategoryList;
    }

    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        // 补全信息
        for (Integer resourceId : roleResourceVo.getResourceIdList()) {
            // 声明一个中间表对象接收参数
            Role_Resource_Relation role_resource_relation = new Role_Resource_Relation();
            // 接收角色id和资源id集合
            role_resource_relation.setRoleId(roleResourceVo.getRoleId());
            role_resource_relation.setResourceId(resourceId);
            // 补全剩余信息
            Date date = new Date();
            role_resource_relation.setCreatedTime(date);
            role_resource_relation.setUpdatedTime(date);
            role_resource_relation.setCreatedBy("system");
            role_resource_relation.setUpdatedBy("system");

            roleMapper.roleContextResource(role_resource_relation);
        }
    }

    @Override
    public void saveRole(Role role) {
        // 补全剩余信息
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);
        role.setCreatedBy("system");
        role.setUpdatedBy("system");

        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        // 补全剩余信息
        Date date = new Date();
        role.setUpdatedTime(date);
        role.setUpdatedBy("system");

        roleMapper.updateRole(role);
    }

    @Override
    public List<Menu> findAllMenu(Integer id) {
        return roleMapper.findAllMenu(id);
    }


}

package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /*
        分页查询&多条件查询
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /*
        修改用户状态
     */
    public void updateUserStatus(User user);

    /*
        用户登录
     */
    public User login(User user);

    /*
        清空用户id角色关系中间表数据
     */
    public void deleteUserContextRole(Integer uid);

    /*
        建立新的用户角色中间表关系数据
     */
    public void userContextRole(User_Role_relation user_role_relation);

    /*
        1. 根据id查询当前用户的角色信息集合
     */
    public List<Role> findUserRelationRoleById(Integer uid);

    /*
        2. 根据角色id, 查询角色拥有的顶级菜单信息
     */
    public List<Menu> findParentMenuByRoleId(List<Integer> ids);

    /*
        3. 根据PID 查询子菜单信息
     */
    public List<Menu> findSubMenuByPid(int pid);

    /*
        4. 获取用户拥有的资源权限信息
     */
    public List<Resource> findResourceByRoleId(List<Integer> ids);

}

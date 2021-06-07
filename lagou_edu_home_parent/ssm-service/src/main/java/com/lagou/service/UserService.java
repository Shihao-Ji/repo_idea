package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.*;

import java.util.List;

public interface UserService {

    /*
        分页查询&多条件查询
     */
    public PageInfo<User> findAllUserByPage(UserVO userVO);

    /*
        修改用户状态
     */
    public void updateUserStatus(int id, String status);

    /*
        用户登录
     */
    public User login(User user) throws Exception;

    /*
        分配角色的回显
     */
    public List<Role> findUserRelationRoleById(Integer uid);

    /*
        建立新的用户角色中间表关系数据
     */
    public void userContextRole(UserVO userVO);

    /*
        获取用户权限
     */
    public ResponseResult getUserPermissions(Integer userid);

}

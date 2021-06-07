package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PageInfo<User> findAllUserByPage(UserVO userVO) {
        // 分页查询
        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());
        List<User> allUserByPage = userMapper.findAllUserByPage(userVO);
        PageInfo<User> userPageInfo = new PageInfo<>(allUserByPage);
        return userPageInfo;
    }

    @Override
    public void updateUserStatus(int id, String status) {
        // 补全信息
        User user = new User();
        user.setId(id);
        user.setStatus(status);
        user.setUpdate_time(new Date());

        // 调用mapper
        userMapper.updateUserStatus(user);
    }

    @Override
    public User login(User user) throws Exception {
        User user2 = userMapper.login(user);
        System.out.println(Md5.md5(user.getPassword(),"lagou"));
        // 判断查询出来的user是否为空或者密码不正确
        if (user2 != null && Md5.verify(user.getPassword(),"lagou",user2.getPassword())){
            return userMapper.login(user);
        } else {
            return null;
        }
    }

    @Override
    public List<Role> findUserRelationRoleById(Integer uid) {
        List<Role> roleList = userMapper.findUserRelationRoleById(uid);
        return roleList;
    }

    @Override
    public void userContextRole(UserVO userVO) {
        // 清空关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        // 建立新的关联关系
        for (Integer roleid : userVO.getRoleIdList()) {
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleid);

            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");
            userMapper.userContextRole(user_role_relation);
        }
    }

    @Override
    public ResponseResult getUserPermissions(Integer userid) {
        // 1. 查询用户具有的角色信息
        List<Role> roleList = userMapper.findUserRelationRoleById(userid);

        // 2. 将roleList里面的角色id提取出来
        ArrayList<Integer> role_ids = new ArrayList<>();
        for (Role role : roleList) {
            role_ids.add(role.getId());
        }

        // 3. 查询所有的父级菜单
        List<Menu> parentMenuList = userMapper.findParentMenuByRoleId(role_ids);

        // 4. 根据父级菜单查询子级菜单
        for (Menu menu : parentMenuList) {
            List<Menu> subMenuList = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenuList);
        }

        // 5. 获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(role_ids);

        // 6.封装数据
        Map<String, Object> map = new HashMap<>();
        map.put("menuList",parentMenuList);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }
}

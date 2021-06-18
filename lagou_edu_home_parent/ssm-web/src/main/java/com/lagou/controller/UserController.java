package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /*
        分页查询&多条件查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo<User> userPageInfo = userService.findAllUserByPage(userVO);
        return new ResponseResult(true,200,"分页多条件查询成功",userPageInfo);
    }

    /*
        修改用户状态
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(int id, String status){
        if("ENABLE".equalsIgnoreCase(status)){
            status = "DISABLE";
        }else{
            status = "ENABLE";
        }
        userService.updateUserStatus(id,status);
        ResponseResult responseResult = new ResponseResult(true,200,"修改用户状态成功",status);
        return responseResult;
    }

    /*
        用户登录验证
     */
    @RequestMapping("/login")
    public ResponseResult login(User user, HttpServletRequest request) throws Exception {

        User user2 = userService.login(user);

        if (user2 != null) {

            String access_token = UUID.randomUUID().toString();

            // 将查询的结果响应给前台
            Map<String, Object> map = new HashMap<>();
            map.put("access_token",access_token);
            map.put("user_id",user2.getId());
            // 把user对象响应给前台，后续登出会用到其它属性
            map.put("user",user2);

            // 保存用户id及access_token到session中
            HttpSession session = request.getSession();
            session.setAttribute("access_token",access_token);
            session.setAttribute("user_id",user2.getId());
            System.out.println("access_token: " + access_token);
            Object sessionToken = session.getAttribute("access_token");
            System.out.println("存进session的token: " + sessionToken);

            return new ResponseResult(true,1,"用户登录成功",map);
        } else {
            return new ResponseResult(true,1,"用户名或密码错误",null);
        }
    }

    /*
        分配角色的回显
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> roleList = userService.findUserRelationRoleById(id);
        return new ResponseResult(true,200,"分配角色回显成功",roleList);
    }

    /*
        分配角色
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){
        userService.userContextRole(userVO);
        return new ResponseResult(true,200,"分配角色成功",null);
    }

    /*
        获取用户权限信息
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermisstions(HttpServletRequest request){
        // 获取请求头中的token
        String header_token = request.getHeader("Authorization");
        System.out.println("请求头中的token: " + header_token);
        // 获取session中的token
        HttpSession session = request.getSession();
        String access_token = (String) session.getAttribute("access_token");
        System.out.println("获取session中的token: " + access_token);

        // 判断
        if (header_token.equalsIgnoreCase(access_token)) {
            Integer user_id = (Integer) session.getAttribute("user_id");
            System.out.println("user_id : " + user_id);
            ResponseResult responseResult = userService.getUserPermissions(user_id);
            return responseResult;
        } else {
            return new ResponseResult(false,400,"获取信息失败","");
        }
    }
}

package com.rxsoft.controller;

import com.rxsoft.orm.model.User;
import com.rxsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户相关
 */
@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //注册
    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public Object regist(String userId,String passWord,String userName,String tel,String email){
        int result = userService.regist(userId,passWord,userName,tel,email);
        return result;
    }
    //登录
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public Object login(String account,String passWord,int loginType){
        User user = userService.login(account,passWord,loginType);
        return user;
    }
    //注销用户
    @RequestMapping(value = "/closeAccount",method = RequestMethod.POST)
    public Object closeAccount(String account){
        int result = userService.closeAccount(account);
        return result;
    }
    //更新用户信息
    @RequestMapping(value = "/updateAccount",method = RequestMethod.POST)
    public Object updateAccount(String userId,String passWord,String userName,String tel,String email,String fileSpace){
        User user =new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPwd(passWord);
        user.setUserPhone(tel);
        user.setUserFileSpace(fileSpace);
        user.setUserEmail(email);
        int result = userService.updateAccount(user);
        return result;
    }
}

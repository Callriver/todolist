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

    @RequestMapping(value = "/regist",method = RequestMethod.POST)
    public Object regist(String userId,String passWord,String userName,String tel,String email){
        int result = userService.regist(userId,passWord,userName,tel,email);
        return "success";
    }
}

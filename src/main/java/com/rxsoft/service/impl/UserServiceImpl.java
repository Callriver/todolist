package com.rxsoft.service.impl;

import com.rxsoft.orm.dao.TaskListMapper;
import com.rxsoft.orm.dao.TaskMapper;
import com.rxsoft.orm.dao.UserMapper;
import com.rxsoft.orm.model.User;
import com.rxsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired(required=true)
    UserMapper userMapper;
    @Autowired(required=true)
    TaskMapper taskMapper;
    @Autowired(required=true)
    TaskListMapper taskListMapper;

    //注册用户
    @Override
    public int regist(String userId, String passWord, String userName, String tel, String email) {
        User user=new User();
        user.setUserId(userId);
        user.setUserName(userName);
        user.setUserPwd(passWord);
        user.setUserEmail(email);
        user.setUserPhone(tel);
        user.setUserFileSpace(UUID.randomUUID().toString());
        int count = userMapper.insert(user);
        if (count>0){
            return count;
        }else{
            return 0;
        }

    }
    //登录
    @Override
    public User login(String account, String passWord, int loginType) {
        User user = new User();
        //类型为1，作为用户ID，类型为2，作为手机号，类型为3，作为邮箱
        if (loginType==1){
            user = userMapper.selectByPrimaryKey(account);
        }else if (loginType==2){
            user = userMapper.selectByTel(account);
        }else if (loginType==3){
            user = userMapper.selectByEmail(account);
        }
        return user;
    }
    //注销账号
    @Override
    public int closeAccount(String account) {
        int result1 = taskMapper.deleteByUserId(account);
        int result2 = taskListMapper.deleteByUserId(account);
        int result3 = userMapper.deleteByPrimaryKey(account);
        int result = result1 + result2 + result3;
        return result;
    }

    @Override
    public int updateAccount(User user) {
        int result = userMapper.updateByPrimaryKey(user);
        return result;
    }
}

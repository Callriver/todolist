package com.rxsoft.service.impl;

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
}

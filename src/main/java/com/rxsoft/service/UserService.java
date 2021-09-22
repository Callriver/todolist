package com.rxsoft.service;


import com.rxsoft.orm.model.User;

public interface UserService {

    int regist(String userId, String passWord, String userName, String tel, String email);

    User login(String account, String passWord, int loginType);

    int closeAccount(String account);

    int updateAccount(User user);
}

package com.rxsoft.orm.dao;

import com.rxsoft.orm.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String account, String passWord);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByEmail(String account, String passWord);

    User selectByTel(String account, String passWord);
}
package com.rxsoft.orm.dao;

import com.rxsoft.orm.model.User;
import java.util.List;

public interface UserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(User record);

    User selectByPrimaryKey(String userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    User selectByEmail(String account);

    User selectByTel(String account);
}
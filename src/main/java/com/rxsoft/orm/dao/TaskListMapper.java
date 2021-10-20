package com.rxsoft.orm.dao;

import com.rxsoft.orm.model.TaskList;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskListMapper {
    int deleteByPrimaryKey(@Param("listId") Integer listId, @Param("userId") String userId);

    int insert(TaskList record);

    TaskList selectByPrimaryKey(@Param("listId") Integer listId, @Param("userId") String userId);

    List<TaskList> selectAll();

    int updateByPrimaryKey(TaskList record);

    Integer selectMaxIdByUserId(String userId);

    int deleteByUserId(String account);
}
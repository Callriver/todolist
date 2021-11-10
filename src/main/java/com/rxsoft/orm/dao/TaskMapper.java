package com.rxsoft.orm.dao;

import com.rxsoft.orm.model.Task;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    int deleteByPrimaryKey(@Param("taskId") Integer taskId, @Param("listId") Integer listId, @Param("userId") String userId);

    int insert(Task record);

    Task selectByPrimaryKey(@Param("taskId") Integer taskId, @Param("listId") Integer listId, @Param("userId") String userId);

    List<Task> selectAll();

    int updateByPrimaryKey(Task record);

    Integer selectMaxIdByUserIdListId(String userId, Integer listId);

    int deleteByUserId(String account);

    int deleteByTaskId(Integer taskId, Integer listId, String userId);
}
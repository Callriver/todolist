package com.rxsoft.orm.dao;

import com.rxsoft.orm.model.Task;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface TaskMapper {
    int deleteByPrimaryKey(@Param("taskId") Integer taskId, @Param("listId") Integer listId);

    int insert(Task record);

    Task selectByPrimaryKey(@Param("taskId") Integer taskId, @Param("listId") Integer listId);

    List<Task> selectAll();

    int updateByPrimaryKey(Task record);

    int deleteByUserId(@Param("userId") String userId);
}
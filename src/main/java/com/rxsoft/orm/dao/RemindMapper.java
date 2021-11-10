package com.rxsoft.orm.dao;

import com.rxsoft.orm.model.Remind;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface RemindMapper {
    int deleteByPrimaryKey(@Param("guid") String guid, @Param("taskId") Integer taskId, @Param("listId") Integer listId, @Param("userId") String userId);

    int insert(Remind record);

    Remind selectByPrimaryKey(@Param("guid") String guid, @Param("taskId") Integer taskId, @Param("listId") Integer listId, @Param("userId") String userId);

    List<Remind> selectAll();

    int updateByPrimaryKey(Remind record);
}
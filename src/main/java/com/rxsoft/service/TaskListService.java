package com.rxsoft.service;

import com.rxsoft.orm.model.TaskList;

import java.util.List;

public interface TaskListService {

    int addTaskList(TaskList taskList);

    int updateTaskList(TaskList taskList);

    int delTaskList(Integer listId, String userId);

    List<TaskList>  qryTaskListByUserId(String userId);
}

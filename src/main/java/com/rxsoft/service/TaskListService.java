package com.rxsoft.service;

import com.rxsoft.orm.model.TaskList;

public interface TaskListService {

    int addTaskList(TaskList taskList);

    int updateTaskList(TaskList taskList);

    int delTaskList(Integer listId, String userId);
}

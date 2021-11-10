package com.rxsoft.service;

import com.rxsoft.orm.model.Task;

public interface TaskService {

    int addTask(Task task);

    int updTask(Task task);

    int delTask(Integer taskId, Integer listId, String userId);
}

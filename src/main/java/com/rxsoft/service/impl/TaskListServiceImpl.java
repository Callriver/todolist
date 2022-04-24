package com.rxsoft.service.impl;

import com.rxsoft.orm.dao.TaskListMapper;
import com.rxsoft.orm.model.TaskList;
import com.rxsoft.service.TaskListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskListServiceImpl implements TaskListService {

    @Autowired(required=true)
    TaskListMapper taskListMapper;

    @Override
    public int addTaskList(TaskList taskList) {
        Integer maxId = taskListMapper.selectMaxIdByUserId(taskList.getUserId());
        if (maxId == null) {
            taskList.setListId(1);
        }else {
            taskList.setListId(maxId+1);
        }
        int result = taskListMapper.insert(taskList);
        return result;
    }

    @Override
    public int updateTaskList(TaskList taskList) {
        int result = taskListMapper.updateByPrimaryKey(taskList);
        return result;
    }

    @Override
    public int delTaskList(Integer listId, String userId) {
        int result = taskListMapper.deleteByPrimaryKey(listId,userId);
        return result;
    }

    @Override
    public List<TaskList> qryTaskListByUserId(String userId) {
        List<TaskList> taskList = taskListMapper.selectByUserId(userId);
        return taskList;
    }

}

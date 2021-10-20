package com.rxsoft.service.impl;

import com.rxsoft.orm.dao.TaskMapper;
import com.rxsoft.orm.model.Task;
import com.rxsoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired(required=true)
    TaskMapper taskMapper;
    @Override
    public int addTask(Task task) {
        Integer maxId = taskMapper.selectMaxIdByUserIdListId(task.getUserId(),task.getListId());
        if(maxId==null){
            task.setTaskId(1);
        }else{
            task.setTaskId(maxId+1);
        }
        int result = taskMapper.insert(task);
        return result;
    }
}

package com.rxsoft.controller;

import com.rxsoft.orm.model.Task;
import com.rxsoft.orm.model.TaskList;
import com.rxsoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
public class TaskController {
    @Autowired
    private TaskService taskService;

    //新增任务
    @RequestMapping(value = "/addTask",method = RequestMethod.POST)
    public Object addTask(String taskName, Integer taskPriority, Integer listId, Integer taskState, Date taskDate,
                          Date taskRemindTime,Integer taskRepetitionPeriod,Integer taskCustDate,String taskNote,
                          String taskAttachment,String userId){

        Task task = new Task();
        task.setTaskName(taskName);
        task.setTaskPriority(taskPriority);
        task.setListId(listId);
        task.setTaskState(taskState);
        task.setTaskDate(taskDate);
        task.setTaskRemindTime(taskRemindTime);
        task.setTaskRepetitionPeriod(taskRepetitionPeriod);
        task.setTaskCustDate(taskCustDate);
        task.setTaskNote(taskNote);
        task.setTaskAttachment(taskAttachment);
        task.setUserId(userId);
        int result = taskService.addTask(task);
        return result;
    }
}

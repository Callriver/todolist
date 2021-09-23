package com.rxsoft.controller;

import com.rxsoft.orm.model.TaskList;
import com.rxsoft.service.TaskListService;
import com.rxsoft.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 任务列表
 */
@RestController
public class TaskListController {
    @Autowired
    private TaskListService taskListService;

    //新增任务列表
    @RequestMapping(value = "/addTaskList",method = RequestMethod.POST)
    public Object addTaskList(String listName,String userId,int listPriority){
        TaskList taskList = new TaskList();
        taskList.setListName(listName);
        taskList.setUserId(userId);
        taskList.setListPriority(listPriority);
        int result = taskListService.addTaskList(taskList);
        return result;
    }
    //修改任务列表
    @RequestMapping(value = "/updateTaskList",method = RequestMethod.POST)
    public Object updateTaskList(Integer listId,String listName,String userId,int listPriority){
        TaskList taskList = new TaskList();
        taskList.setListId(listId);
        taskList.setListName(listName);
        taskList.setUserId(userId);
        taskList.setListPriority(listPriority);
        int result = taskListService.updateTaskList(taskList);
        return result;
    }
    //删除任务列表
    @RequestMapping(value = "/updateTaskList",method = RequestMethod.POST)
    public Object delTaskList(Integer listId,String userId){
        int result = taskListService.delTaskList(listId,userId);
        return result;
    }
}

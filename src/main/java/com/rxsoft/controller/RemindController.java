package com.rxsoft.controller;

import com.rxsoft.orm.model.Remind;
import com.rxsoft.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
public class RemindController {
    @Autowired
    private RemindService remindService;

    /**
     * 查询当前用户当日提醒节点
     */
    @RequestMapping(value = "/qryRemindByDay",method = RequestMethod.POST)
    public Object qryRemindByDay(String userId){
        List<Remind> remindLists = remindService.qryRemindByDay(userId);
        return remindLists;
    }
}

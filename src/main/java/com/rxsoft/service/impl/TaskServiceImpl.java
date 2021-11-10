package com.rxsoft.service.impl;

import com.rxsoft.orm.dao.RemindMapper;
import com.rxsoft.orm.dao.TaskMapper;
import com.rxsoft.orm.model.Remind;
import com.rxsoft.orm.model.Task;
import com.rxsoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

@Service
public class TaskServiceImpl implements TaskService {
    @Autowired(required=true)
    TaskMapper taskMapper;
    @Autowired(required=true)
    RemindMapper remindMapper;
    @Override
    public int addTask(Task task) {
        Integer maxId = taskMapper.selectMaxIdByUserIdListId(task.getUserId(),task.getListId());
        if(maxId==null){
            task.setTaskId(1);
        }else{
            task.setTaskId(maxId+1);
        }
        int result = taskMapper.insert(task);

        //获取循环次数
        int cycles = 0;
        if (task.getTaskRepetitionPeriod()==1)
        {
            //如果按天提醒，算两日期的间隔天数
            try {
                cycles = daysBetween(task.getTaskRemindTime(),task.getTaskDate());
                System.out.println(cycles);
                //循环写提醒队列
                for (int i=1;i<=cycles;i++)
                {
                    Calendar c = Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.DATE,i);
                    Remind remind = new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setListId(task.getListId());
                    remind.setTaskId(task.getTaskId());
                    remind.setUserId(task.getUserId());
                    remind.setRemindDate(c.getTime());
                    remindMapper.insert(remind);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (task.getTaskRepetitionPeriod()==2)
        {
            //如果按工作日提醒，算两日期的间隔天数,如果非工作日不写insert
            try {
                cycles = daysBetween(task.getTaskRemindTime(),task.getTaskDate());
                //循环写提醒队列
                for (int i=1;i<=cycles;i++)
                {
                    Calendar c = Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.DATE,i);
                    Remind remind = new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setListId(task.getListId());
                    remind.setTaskId(task.getTaskId());
                    remind.setUserId(task.getUserId());
                    remind.setRemindDate(c.getTime());
                    if (c.get(Calendar.DAY_OF_WEEK-1)!=6&&c.get(Calendar.DAY_OF_WEEK-1)!=7)
                    {
                        remindMapper.insert(remind);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    @Override
    public int updTask(Task task) {
        int result = taskMapper.updateByPrimaryKey(task);
        return result;
    }

    @Override
    public int delTask(Integer taskId, Integer listId, String userId) {
        int result = taskMapper.deleteByTaskId(taskId,listId,userId);
        return result;
    }

    //算间隔天数
    public int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days=(time2-time1)/(1000*3600*24);

        return Integer.parseInt(String.valueOf(between_days));
    }


}

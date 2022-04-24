package com.rxsoft.service.impl;

import com.rxsoft.orm.dao.RemindMapper;
import com.rxsoft.orm.dao.TaskMapper;
import com.rxsoft.orm.model.Remind;
import com.rxsoft.orm.model.Task;
import com.rxsoft.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
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

    //开启事务
    @Transactional
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
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
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
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    //System.out.println(c.getTime()+"-"+(c.get(Calendar.DAY_OF_WEEK)));
                    if (c.get(Calendar.DAY_OF_WEEK)!=1 && c.get(Calendar.DAY_OF_WEEK)!=7)
                    {
                        remindMapper.insert(remind);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(task.getTaskRepetitionPeriod()==3)
        {
            //如果按每周提醒一次
            //获取间隔周数
            try {
                cycles=weekBetween(task.getTaskRemindTime(),task.getTaskDate());
                System.out.println(cycles);

                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.WEEK_OF_YEAR,i);

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(task.getTaskRepetitionPeriod()==4)
        {
            //每月提醒一次
            try {
                cycles=monthBetween(task.getTaskRemindTime(),task.getTaskDate());
                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.MONTH,i);

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(task.getTaskRepetitionPeriod()==5)
        {
            //每年提醒一次
            try {
                cycles=yearBetween(task.getTaskRemindTime(),task.getTaskDate());
                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.YEAR,i);

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (task.getTaskRepetitionPeriod()==0)
        {
            //自定义循环天数
            try {
                cycles=customBetween(task.getTaskRemindTime(),task.getTaskDate(),task.getTaskCustDate());
                System.out.println(cycles);
                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.DAY_OF_MONTH,i*task.getTaskCustDate());

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //开启事务
    @Transactional
    @Override
    public int updTask(Task task) {
        int result = taskMapper.updateByPrimaryKey(task);
        //删除任务对应remind表数据
        remindMapper.deleteByTaskId(task.getTaskId(),task.getListId(),task.getUserId());

        //定义循环次数
        int cycles = 0;
        //每次修改任务后重写提醒对列表
        if (task.getTaskRepetitionPeriod()==1)
        {
            //如果按天提醒，算两日期的间隔天数
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
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
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
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    //System.out.println(c.getTime()+"-"+(c.get(Calendar.DAY_OF_WEEK)));
                    if (c.get(Calendar.DAY_OF_WEEK)!=1 && c.get(Calendar.DAY_OF_WEEK)!=7)
                    {
                        remindMapper.insert(remind);
                    }
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(task.getTaskRepetitionPeriod()==3)
        {
            //如果按每周提醒一次
            //获取间隔周数
            try {
                cycles=weekBetween(task.getTaskRemindTime(),task.getTaskDate());
                System.out.println(cycles);

                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.WEEK_OF_YEAR,i);

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(task.getTaskRepetitionPeriod()==4)
        {
            //每月提醒一次
            try {
                cycles=monthBetween(task.getTaskRemindTime(),task.getTaskDate());
                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.MONTH,i);

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if(task.getTaskRepetitionPeriod()==5)
        {
            //每年提醒一次
            try {
                cycles=yearBetween(task.getTaskRemindTime(),task.getTaskDate());
                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.YEAR,i);

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }else if (task.getTaskRepetitionPeriod()==0)
        {
            //自定义循环天数
            try {
                cycles=customBetween(task.getTaskRemindTime(),task.getTaskDate(),task.getTaskCustDate());
                System.out.println(cycles);
                //循环写提醒队列
                for(int i=1;i<=cycles;i++)
                {
                    Calendar c=Calendar.getInstance();
                    c.setTime(task.getTaskRemindTime());
                    c.add(Calendar.DAY_OF_MONTH,i*task.getTaskCustDate());

                    Remind remind=new Remind();
                    remind.setGuid(UUID.randomUUID().toString());
                    remind.setUserId(task.getUserId());
                    remind.setTaskId(task.getTaskId());
                    remind.setListId(task.getListId());
                    remind.setRemindDate(new java.sql.Timestamp(c.getTime().getTime()));
                    remindMapper.insert(remind);
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    //删除任务后清空remind对应数据
    @Transactional
    @Override
    public int delTask(Integer taskId, Integer listId, String userId) {
        int result = taskMapper.deleteByTaskId(taskId,listId,userId);
        remindMapper.deleteByTaskId(taskId,listId,userId);
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
    //算间隔周数
    public int weekBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_weeks=(time2-time1)/(1000*3600*24*7);

        return Integer.parseInt(String.valueOf(between_weeks));
    }

    //算间隔月数
    public int monthBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_months=(time2-time1)/(1000*3600*24*30);

        return Integer.parseInt(String.valueOf(between_months));
    }

    //算间隔年份
    public int yearBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_years=(time2-time1)/(1000*3600*24*365);

        return Integer.parseInt(String.valueOf(between_years));
    }

    //算自定义天数循环次数
    public int customBetween(Date smdate, Date bdate,int customDay) throws ParseException {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        smdate=sdf.parse(sdf.format(smdate));
        bdate=sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_years=(time2-time1)/(1000*3600*24*customDay);

        return Integer.parseInt(String.valueOf(between_years));
    }

}

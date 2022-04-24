package com.rxsoft.service.impl;

import com.rxsoft.orm.dao.RemindMapper;
import com.rxsoft.orm.model.Remind;
import com.rxsoft.service.RemindService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class RemindServiceImpl implements RemindService {
    @Autowired
    private RemindMapper remindMapper;
    @Override
    public List<Remind> qryRemindByDay(String userId) {
        List<Remind> reminds = remindMapper.selectByCurentDay(userId);
        for (Remind remind:
             reminds) {
            System.out.println(remind.getRemindDate());
        }
        return reminds;
    }
}

package com.rxsoft.service;

import com.rxsoft.orm.model.Remind;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

public interface RemindService {

    List<Remind> qryRemindByDay(String userId);
}

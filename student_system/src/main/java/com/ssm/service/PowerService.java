package com.ssm.service;

import com.ssm.dao.PowerDao;
import com.ssm.entity.Power;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PowerService {

    @Autowired
    PowerDao powerDao;

    public void needPower(Integer teacherId){
        powerDao.needPower(teacherId);
    }

    public void deletePower(Integer teacherId){
        powerDao.deletePower(teacherId);
    }

    public Power queryByTeacherId(Integer teacherId){
        return powerDao.queryByTeacherId(teacherId);
    }

    public void create(Power power) {
        powerDao.create(power);
    }
}

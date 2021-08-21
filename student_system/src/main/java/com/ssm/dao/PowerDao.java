package com.ssm.dao;

import com.ssm.entity.Power;

public interface PowerDao {

    public void needPower(Integer id);

    public void deletePower(Integer id);

    Power queryByTeacherId(Integer teacherId);

    void create(Power power);
}

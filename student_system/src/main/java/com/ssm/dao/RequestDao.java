package com.ssm.dao;

import com.ssm.entity.Request;
import com.ssm.entity.Student;

import java.util.List;
import java.util.Map;

public interface RequestDao {
    public int create(Request pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);


    public List<Request> query(Map<String, Object> paramMap);

    public List<Request> studentQuery(Map<String, Object> paramMap);

    public Request detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    public int update1(Map<String, Object> map);

    Request queryById(int parseInt);

    List<Request> userQuery(Map<String, Object> beanToMap);

    int refuse(Map<String, Object> map);

    void deleteByStuId(Integer stuId);

    Request queryByStuId(Integer stuId);

    Request updateQueryById(Integer id);
}
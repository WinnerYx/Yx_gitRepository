package com.ssm.dao;

import com.ssm.entity.StudentAction;

import java.util.List;
import java.util.Map;

public interface StudentActionDao {
    public int create(StudentAction pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<StudentAction> query(Map<String, Object> paramMap);

    public StudentAction detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    public int update1(Map<String, Object> map);

    int refuse(Map<String, Object> map);

    StudentAction queryById(int id);

    StudentAction queryByStuId(Integer stuId);

    StudentAction updateQueryById(Integer id);
}
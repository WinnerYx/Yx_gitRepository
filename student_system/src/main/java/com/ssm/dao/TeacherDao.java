package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.entity.Teacher;

public interface TeacherDao {
    public int create(Teacher pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Teacher> query(Map<String, Object> paramMap);

    public Teacher detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);


    Teacher queryByTeacherId(Integer id);

    int updateAll(Map<String, Object> power);

    Teacher queryByTeacherName(String name);
}
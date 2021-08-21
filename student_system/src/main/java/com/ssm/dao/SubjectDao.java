package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.entity.Subject;

public interface SubjectDao {
    public int create(Subject pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Subject> query(Map<String, Object> paramMap);

    public Subject detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    public Subject queryById(Integer subjectId);

    Subject queryByName(String name);
}
package com.ssm.dao;

import com.ssm.entity.Request;
import com.ssm.entity.StudyStatus;

import java.util.List;
import java.util.Map;

public interface StudyStatusDao {
    public int create(StudyStatus pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<StudyStatus> query(Map<String, Object> paramMap);

    public StudyStatus detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    int refuse(Map<String, Object> map);

    StudyStatus queryById(int id);

    StudyStatus queryByStuId(Integer stuId);

    int update1(Map<String, Object> map);

    StudyStatus updateQueryById(Integer id);
}
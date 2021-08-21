package com.ssm.dao;

import java.util.List;
import java.util.Map;

import com.ssm.entity.Job;

public interface JobDao {
    public int create(Job pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Job> query(Map<String, Object> paramMap);

    public List<Job> queryJob(Map<String, Object> paramMap);

    public Job detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    public Job queryJobById(Integer jobId);

    int createJob(Job job);

    int updateJob(Map<String, Object> map);

    List<Job> queryListJobStatus(Map<String, Object> beanToMap);

    List<Job> queryRequestJob(Map<String, Object> beanToMap);

    Job queryById(int id);

    int refuse(Map<String, Object> map);

    Job queryByStuId(Integer stuId);
}
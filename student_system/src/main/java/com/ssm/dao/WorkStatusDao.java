package com.ssm.dao;

import com.ssm.entity.Job;
import com.ssm.entity.WorkStatus;
import java.util.List;
import java.util.Map;


public interface WorkStatusDao {

    public List<WorkStatus> queryStudentWorkStatus(Integer studentId);

    public List<WorkStatus> query(WorkStatus workStatus);

    public int create(WorkStatus pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<WorkStatus> query(Map<String, Object> paramMap);

    public WorkStatus detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    public List<WorkStatus> queryByStuId(Integer stuId);
}

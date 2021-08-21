package com.ssm.dao;

import com.ssm.entity.StudentCount;

public interface StudentCountDao {
    public void createCount(StudentCount studentCount);

    public void increaseRequestCount(Integer studentId);

    public void decreaseRequestCount(Integer studentId);

    public void increaseStudyStatusCount(Integer studentId);

    public void decreaseStudyStatusCount(Integer studentId);

    public void increaseStudentActionCount(Integer studentId);

    public void decreaseStudentActionCount(Integer studentId);

    public void increaseJobCount(Integer studentId);

    public void decreaseJobCount(Integer studentId);

    public StudentCount queryById(Integer studentId);
}

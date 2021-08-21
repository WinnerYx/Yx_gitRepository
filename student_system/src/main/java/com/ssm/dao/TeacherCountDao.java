package com.ssm.dao;

import com.ssm.entity.TeacherCount;

public interface TeacherCountDao{
    public void createCount(TeacherCount teacherCount);

    public void increaseRequestCount(Integer teacherId);

    public void decreaseRequestCount(Integer teacherId);

    public void increaseStudyStatusCount(Integer teacherId);

    public void decreaseStudyStatusCount(Integer teacherId);

    public void increaseStudentActionCount(Integer teacherId);

    public void decreaseStudentActionCount(Integer teacherId);

    public void increaseJobCount(Integer teacherId);

    public void decreaseJobCount(Integer teacherId);

    public void decreaseAnnouncementCount(Integer teacherId);

    public TeacherCount queryById(Integer teacherId);

    void increaseAnnouncementCount(Integer teacherId);
}

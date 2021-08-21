package com.ssm.service;

import com.ssm.dao.TeacherCountDao;
import com.ssm.entity.TeacherCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TeacherCountService extends CountService {

    @Autowired
    TeacherCountDao teacherCountDao;

    public void createCount(TeacherCount teacherCount){
       teacherCountDao.createCount(teacherCount);
    }


    public TeacherCount queryById(Integer teacherId){
        return teacherCountDao.queryById(teacherId);
    }

    public void increaseRequestCount(Integer teacherId){
        teacherCountDao.increaseRequestCount(teacherId);
    }
    public void decreaseRequestCount(Integer teacherId){
        teacherCountDao.decreaseRequestCount(teacherId);
    }

    public void increaseStudyStatusCount(Integer teacherId){
        teacherCountDao.increaseStudyStatusCount(teacherId);
    }
    public void decreaseStudyStatusCount(Integer teacherId){
        teacherCountDao.decreaseStudyStatusCount(teacherId);
    }

    public void increaseStudentActionCount(Integer teacherId){
        teacherCountDao.increaseStudentActionCount(teacherId);
    }
    public void decreaseStudentActionCount(Integer teacherId){
        teacherCountDao.decreaseStudentActionCount(teacherId);
    }

    public void increaseJobCount(Integer teacherId){
        teacherCountDao.increaseJobCount(teacherId);
    }
    public void decreaseJobCount(Integer teacherId){
        teacherCountDao.decreaseJobCount(teacherId);
    }


    public void decreaseAnnouncementCount(Integer teacherId) {
        teacherCountDao.decreaseAnnouncementCount(teacherId);
    }

    public void increaseAnnouncementCount(Integer teacherId) {
        teacherCountDao.increaseAnnouncementCount(teacherId);
    }
}

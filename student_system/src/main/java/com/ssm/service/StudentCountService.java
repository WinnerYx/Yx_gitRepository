package com.ssm.service;

import com.ssm.dao.StudentCountDao;
import com.ssm.entity.StudentCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentCountService extends CountService {

    @Autowired
    StudentCountDao studentCountDao;

    public void createCount(StudentCount studentCount){
        studentCountDao.createCount(studentCount);
    }


    public StudentCount queryById(Integer studentId){
        return studentCountDao.queryById(studentId);
    }

    public void increaseRequestCount(Integer studentId){
        studentCountDao.increaseRequestCount(studentId);
    }
    public void decreaseRequestCount(Integer studentId){
        studentCountDao.decreaseRequestCount(studentId);
    }

    public void increaseStudyStatusCount(Integer studentId){
        studentCountDao.increaseStudyStatusCount(studentId);
    }
    public void decreaseStudyStatusCount(Integer studentId){
        studentCountDao.decreaseStudyStatusCount(studentId);
    }

    public void increaseStudentActionCount(Integer studentId){
        studentCountDao.increaseStudentActionCount(studentId);
    }
    public void decreaseStudentActionCount(Integer studentId){
        studentCountDao.decreaseStudentActionCount(studentId);
    }

    public void increaseJobCount(Integer studentId){
        studentCountDao.increaseJobCount(studentId);
    }
    public void decreaseJobCount(Integer studentId){
        studentCountDao.decreaseJobCount(studentId);
    }



}

package com.ssm.service;

import com.ssm.dao.*;
import com.ssm.entity.P_Count;
import com.ssm.entity.StudentCount;
import com.ssm.entity.TeacherCount;
import com.ssm.entity.UserCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CountService {
    @Autowired
    TeacherCountDao teacherCountDao;
    @Autowired
    UserCountDao userCountDao;
    @Autowired
    StudentCountDao studentCountDao;
    @Autowired
    StudentCountService studentCountService;
    @Autowired
    UserCountService userCountService;
    @Autowired
    TeacherCountService teacherCountService;

    public P_Count queryById(Integer id, int type){
        if(type==1){
            UserCount userCount = null;
            userCount = userCountService.queryById(id);
            if(userCount==null){
                userCount = new UserCount();
                userCount.setUserId(id);
                userCount.setRequestCount(0);
                userCount.setAnnouncementCount(0);
                userCountService.createCount(userCount);
            }
            return userCountDao.queryById(id);
        }else if(type==2){
            TeacherCount teacherCount = null;
            teacherCount = teacherCountService.queryById(id);
            if(teacherCount==null){
                teacherCount = new TeacherCount();
                teacherCount.setTeacherId(id);
                teacherCount.setJobCount(0);
                teacherCount.setStudentActionCount(0);
                teacherCount.setRequestCount(0);
                teacherCount.setStudyStatusCount(0);
                teacherCountService.createCount(teacherCount);
            }
            return teacherCountDao.queryById(id);
        }else {
            StudentCount studentCount = null;
            studentCount = studentCountService.queryById(id);
            if(studentCount==null){
                studentCount = new StudentCount();
                studentCount.setStudentId(id);
                studentCount.setJobCount(0);
                studentCount.setStudentActionCount(0);
                studentCount.setRequestCount(0);
                studentCount.setStudyStatusCount(0);
                studentCountService.createCount(studentCount);
            }
            return studentCountDao.queryById(id);
        }

    }
}

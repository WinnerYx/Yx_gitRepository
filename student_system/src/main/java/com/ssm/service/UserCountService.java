package com.ssm.service;

import com.ssm.dao.UserCountDao;
import com.ssm.entity.UserCount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserCountService extends CountService {

    @Autowired
    UserCountDao userCountDao;

    public void createCount(UserCount userCount){
        userCountDao.createCount(userCount);
    }


    public UserCount queryById(Integer userId){
        return userCountDao.queryById(userId);
    }

    public void increaseRequestCount(Integer userId){
        userCountDao.increaseRequestCount(userId);
    }
    public void decreaseRequestCount(Integer userId){
        userCountDao.decreaseRequestCount(userId);
    }

    public void increaseStudyStatusCount(Integer userId){
        userCountDao.increaseStudyStatusCount(userId);
    }
    public void decreaseStudyStatusCount(Integer userId){
        userCountDao.decreaseStudyStatusCount(userId);
    }

    public void increaseStudentActionCount(Integer userId){
        userCountDao.increaseStudentActionCount(userId);
    }
    public void decreaseStudentActionCount(Integer userId){
        userCountDao.decreaseStudentActionCount(userId);
    }

    public void increaseJobCount(Integer userId){
        userCountDao.increaseJobCount(userId);
    }
    public void decreaseJobCount(Integer userId){
        userCountDao.decreaseJobCount(userId);
    }

    public void increaseAnnouncementCount(Integer userId){
         userCountDao.increaseAnnouncementCount(userId);
    };
    public void decreaseAnnouncementCount(Integer userId){
        userCountDao.decreaseAnnouncementCount(userId);
    }



}

package com.ssm.dao;

import com.ssm.entity.UserCount;

public interface UserCountDao {
    public void createCount(UserCount userCount);

    public void increaseRequestCount(Integer userId);

    public void decreaseRequestCount(Integer userId);

    public void increaseStudyStatusCount(Integer userId);

    public void decreaseStudyStatusCount(Integer userId);

    public void increaseStudentActionCount(Integer userId);

    public void decreaseStudentActionCount(Integer userId);

    public void increaseJobCount(Integer userId);

    public void decreaseJobCount(Integer userId);
    public void increaseAnnouncementCount(Integer userId);

    public void decreaseAnnouncementCount(Integer userId);

    UserCount queryById(Integer userId);
}

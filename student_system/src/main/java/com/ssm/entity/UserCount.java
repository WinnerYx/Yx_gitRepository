package com.ssm.entity;

public class UserCount implements P_Count {
    private int requestCount;
    private int announcementCount;
    private int studyStatusCount;
    private int studentActionCount;
    private int jobCount;
    private int userId;
    private int id;

    @Override
    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public int getRequestCount() {
        return requestCount;
    }

    public void setRequestCount(int requestCount) {
        this.requestCount = requestCount;
    }

    @Override
    public int getStudyStatusCount() {
        return studyStatusCount;
    }

    public void setAnnouncementCount(int announcementCount) {
        this.announcementCount = announcementCount;
    }

    @Override
    public int getAnnouncementCount() {
        return announcementCount;
    }

    public void setStudyStatusCount(int studyStatusCount) {
        this.studyStatusCount = studyStatusCount;
    }

    @Override
    public int getStudentActionCount() {
        return studentActionCount;
    }

    public void setStudentActionCount(int studentActionCount) {
        this.studentActionCount = studentActionCount;
    }


}

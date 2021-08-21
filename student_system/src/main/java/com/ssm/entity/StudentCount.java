package com.ssm.entity;

public class StudentCount implements P_Count {
    private int id;
    private int requestCount;
    private int studyStatusCount;
    private int studentActionCount;
    private int studentId;
    private int jobCount;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    @Override
    public int getAnnouncementCount() {
        return 0;
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

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }
}

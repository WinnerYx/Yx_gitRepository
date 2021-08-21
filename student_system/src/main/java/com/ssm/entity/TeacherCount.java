package com.ssm.entity;

public class TeacherCount implements P_Count {
    private int requestCount;
    private int studyStatusCount;
    private int studentActionCount;
    private int jobCount;
    private int teacherId;
    private int id;

    @Override
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(int teacherId) {
        this.teacherId = teacherId;
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

    @Override
    public int getJobCount() {
        return jobCount;
    }

    public void setJobCount(int jobCount) {
        this.jobCount = jobCount;
    }
}

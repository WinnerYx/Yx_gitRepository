package com.ssm.entity;

public class TeacherWorkHistory {
    private Integer id;
    private int workHistory;
    private int studentCount;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getWorkHistory() {
        return workHistory;
    }

    public void setWorkHistory(int workHistory) {
        this.workHistory = workHistory;
    }

    public int getStudentCount() {
        return studentCount;
    }

    public void setStudentCount(int studentCount) {
        this.studentCount = studentCount;
    }
}

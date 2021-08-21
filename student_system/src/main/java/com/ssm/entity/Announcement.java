package com.ssm.entity;

import com.ssm.utils.Entity;


import java.util.Date;

public class Announcement extends Entity {

    private Integer id;
    private String content;
    private String status;
    private String result1;
    private Date publishTime;
    private Integer teacherId;
    private Teacher teacher;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public String toString() {
        return "Announcement{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", status='" + status + '\'' +
                ", result1='" + result1 + '\'' +
                ", publishTime=" + publishTime +
                ", teacherId=" + teacherId +
                ", teacher=" + teacher +
                ", type='" + type + '\'' +
                '}';
    }
}

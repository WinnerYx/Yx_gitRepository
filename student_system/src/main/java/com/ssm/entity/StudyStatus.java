package com.ssm.entity;

import com.ssm.utils.Entity;

public class StudyStatus extends Entity {

    private Integer id;
    private String reason;
    private Integer stuId;
    private String result1;
    private String status;
    private Student student;
    private Integer days;
    private int teacherCount;
    private int userCount;
    private String type;




    /*public  enum  StatusType{

        status_1(1,"待老师审批"),
        status_2(2,"老师已审批，待管理员审批"),
        status_3(3,"审批完成"),
        status_4(4,"审批拒绝");

        private Integer val;
        private String msg;

        StatusType(Integer val,String msg){
            this.val = val;
            this.msg = msg;
        }

        public Integer getVal() {
            return val;
        }

        public void setVal(Integer val) {
            this.val = val;
        }

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }
    }*/

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public String getResult1() {
        return result1;
    }

    public void setResult1(String result1) {
        this.result1 = result1;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Integer getDays() {
        return days;
    }

    public void setDays(Integer days) {
        this.days = days;
    }

    public int getTeacherCount() {
        return teacherCount;
    }

    public void setTeacherCount(int teacherCount) {
        this.teacherCount = teacherCount;
    }

    public int getUserCount() {
        return userCount;
    }

    public void setUserCount(int userCount) {
        this.userCount = userCount;
    }
}

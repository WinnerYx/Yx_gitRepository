package com.ssm.entity;

import com.ssm.utils.Entity;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class WorkStatus extends Entity {
    private Integer id;
    private String company;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date workTime;
    private String workCertify;
    private double salary;
    private Integer stuId;

    public Integer getStuId() {
        return stuId;
    }

    public void setStuId(Integer stuId) {
        this.stuId = stuId;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
    }

    public String getWorkCertify() {
        return workCertify;
    }

    public void setWorkCertify(String workCertify) {
        this.workCertify = workCertify;
    }
}

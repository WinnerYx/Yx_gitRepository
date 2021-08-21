package com.ssm.entity;

import com.ssm.utils.Entity;

/**
 * 
 * @author 596183363@qq.com
 * @time 2020-06-19 10:28:13
 */
public class Job extends Entity {

	private Integer id;
	private String name;
	private String workPlace;
	private String workTime;
	private double salary;
	private Integer stuId;
	private Student student;
	private String jobStatus;
	private String result1;

	public String getResult1() {
		return result1;
	}

	public void setResult1(String result1) {
		this.result1 = result1;
	}

	public String getJobStatus() {
		return jobStatus;
	}

	public void setJobAgree(String jobStatus) {
		this.jobStatus = jobStatus;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getWorkPlace() {
		return workPlace;
	}

	public void setWorkPlace(String workPlace) {
		this.workPlace = workPlace;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public Integer getStuId() {
		return stuId;
	}

	public void setStuId(Integer stuId) {
		this.stuId = stuId;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
}
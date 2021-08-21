package com.ssm.entity;

import com.ssm.utils.Entity;

import java.util.Date;

/**
 * 
 * @author 596183363@qq.com
 * @time 2020-06-19 10:28:13
 */
public class Teacher  extends Entity implements Person {

	private String power;

	private String needPower;

	public String getNeedPower() {
		return needPower;
	}

	public void setNeedPower(String needPower) {
		this.needPower = needPower;
	}

	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private String remark;
	/**
	 * 
	 */
	private String teacherPwd;
	/**
	 * 
	 */
	private String teacherName;


	private String name;

	private Date joinDate;

	public Date geJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTeacherPwd() {
		return teacherPwd;
	}

	public void setTeacherPwd(String teacherPwd) {
		this.teacherPwd = teacherPwd;
	}

	public String getTeacherName() {
		return teacherName;
	}
	public void setTeacherName(String teacherName) {
		this.teacherName = teacherName;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}


}
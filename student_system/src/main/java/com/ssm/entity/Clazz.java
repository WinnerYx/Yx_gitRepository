package com.ssm.entity;


import com.ssm.utils.Entity;

/**
 * 
 * @author 596183363@qq.com
 * @time 2020-06-19 10:28:13
 */
public class Clazz extends Entity {

	/**
	 * 
	 */
	private String clazzName;
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
	private Integer subjectId;

	private Subject subject;

	private Teacher teacher;

	private Integer teacherId;

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

	public String getClazzName() {
		return clazzName;
	}
	public void setClazzName(String clazzName) {
		this.clazzName = clazzName;
	}
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
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}
}
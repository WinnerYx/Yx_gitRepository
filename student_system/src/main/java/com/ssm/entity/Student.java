package com.ssm.entity;

import com.ssm.utils.Entity;

import java.util.Date;

/**
 * 
 * @author 596183363@qq.com
 * @time 2020-06-19 10:28:13
 */
public class Student  extends Entity implements Person {


	/**
	 * 
	 */
	private String addr;
	/**
	 * 
	 */
	private String gender;
	/**
	 * 
	 */
	private Date birthday;
	/**
	 * 
	 */
	private String cardNo;
	/**
	 * 
	 */
	private Integer clazzId;
	/**
	 * 
	 */
	private Integer id;
	/**
	 * 
	 */
	private Date joinDate;
	/**
	 * 
	 */
	private String phone;
	/**
	 * 
	 */
	private String pname;
	/**
	 * 
	 */
	private String status;
	/**
	 * 
	 */
	private String stuName;
	/**
	 * 
	 */
	private String stuNo;
	/**
	 * 
	 */
	private String stuPwd;
	/**
	 * 
	 */
	private Integer subjectId;
	/**
	 * 
	 */
	private String telephone;

	private Subject subject;

	private Clazz clazz;

	private Teacher teacher;

	private Integer teacherId;

	private WorkStatus workStatus;

	private String flag;

	public Student(String stuNo, String stuName, String stuPwd, String cardNo, String gender, Date birthday, String phone,
				   String pname, String telephone, String addr, Date joinDate, String status, Integer clazzId, Integer subjectId, Integer teacherId, String flag) {
		this.stuNo=stuNo;
		this.stuName=stuName;
		this.stuPwd=stuPwd;
		this.cardNo=cardNo;
		this.gender=gender;
		this.birthday=birthday;
		this.phone=phone;
		this.pname=pname;
		this.telephone=telephone;
		this.addr=addr;
		this.joinDate=joinDate;
		this.status=status;
		this.clazzId=clazzId;
		this.subjectId=subjectId;
		this.teacherId=teacherId;
		this.flag=flag;
	}

	public Teacher getTeacher() {
		return teacher;
	}

	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	public Student() {
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public WorkStatus getWorkStatus() {
		return workStatus;
	}

	public void setWorkStatus(WorkStatus workStatus) {
		this.workStatus = workStatus;
	}

	public Integer getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(Integer teacherId) {
		this.teacherId = teacherId;
	}

	public String getAddr() {
		return addr;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public Integer getClazzId() {
		return clazzId;
	}
	public void setClazzId(Integer clazzId) {
		this.clazzId = clazzId;
	}
	@Override
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getStuName() {
		return stuName;
	}
	public void setStuName(String stuName) {
		this.stuName = stuName;
	}
	public String getStuNo() {
		return stuNo;
	}
	public void setStuNo(String stuNo) {
		this.stuNo = stuNo;
	}
	public String getStuPwd() {
		return stuPwd;
	}
	public void setStuPwd(String stuPwd) {
		this.stuPwd = stuPwd;
	}
	public Integer getSubjectId() {
		return subjectId;
	}
	public void setSubjectId(Integer subjectId) {
		this.subjectId = subjectId;
	}
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public static class StatusType{
		public static String type_1 = "正常";
		public static String type_2 = "辍学";
		public static String type_3 = "休学";
		public static String type_4 = "复学";
		public static String type_5 = "转学";
		public static String type_6 = "转入";
		public static String type_7 = "毕业";
	}

	@Override
	public String toString() {
		return "Student{" +
				"addr='" + addr + '\'' +
				", gender='" + gender + '\'' +
				", birthday=" + birthday +
				", cardNo='" + cardNo + '\'' +
				", clazzId=" + clazzId +
				", id=" + id +
				", joinDate=" + joinDate +
				", phone='" + phone + '\'' +
				", pname='" + pname + '\'' +
				", status='" + status + '\'' +
				", stuName='" + stuName + '\'' +
				", stuNo='" + stuNo + '\'' +
				", stuPwd='" + stuPwd + '\'' +
				", subjectId=" + subjectId +
				", telephone='" + telephone + '\'' +
				", subject=" + subject +
				", clazz=" + clazz +
				", teacherId=" + teacherId +
				", workStatus=" + workStatus +
				", flag='" + flag + '\'' +
				'}';
	}
}
package com.ssm.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.ssm.entity.Student;
import com.ssm.entity.Teacher;

public interface StudentDao {
    public int create(Student pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Student> query(Map<String, Object> paramMap);


    public Student detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    public List<HashMap> querySelectStudent(Map<String, Object> paramMap);

    public List<Student> queryStudentByTeacher(Map<String, Object> paramMap);

    public List<Student> queryStudentByTeacher1(Map<String, Object> paramMap);

    public List<Student> queryStudentByUser(Map<String, Object> paramMap);


    public Student queryById(Integer id);

    List<Student> queryListById(Integer id);

    List<Student> queryByClazzIdAndPoor(Map<String, Object> map);

    Student queryByClazzIdAndWork(Map<String, Object> map);
}
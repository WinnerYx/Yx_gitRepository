package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.StudentDao;
import com.ssm.entity.Student;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MD5Utils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    public int create(Student pi) {
        pi.setStuPwd(MD5Utils.getMD5(pi.getStuPwd()));
        return studentDao.create(pi);
    }

    public int delete(Integer id) {
        return studentDao.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studentDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Student student) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(student)).addId(student.getId()).getMap();
        return studentDao.update(map);
    }

    public List<Student> query(Student student) {
        if(student != null && student.getPage() != null){
            PageHelper.startPage(student.getPage(),student.getLimit());
        }
        return studentDao.query(BeanMapUtils.beanToMap(student));
    }

    public Student queryById(Integer id) {
        return studentDao.queryById(id);
    }

    public Student detail(Integer id) {
        return studentDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Student student) {
        return studentDao.count(BeanMapUtils.beanToMap(student));
    }


    public Student login(String userName, String password){
        Map<String, Object> map = MapParameter.getInstance()
                .add("stuNo", userName)
                .add("stuPwd", password)
                .getMap();
        return studentDao.detail(map);
    }

    public List<HashMap> querySelectStudent(Integer courseId, Integer sectionId){
        Map<String, Object> map = MapParameter.getInstance()
                .add("courseId", courseId)
                .add("sectionId", sectionId)
                .getMap();
        return studentDao.querySelectStudent(map);
    }

    public List<Student> queryStudentByTeacher(Student student){
        if(student != null && student.getPage() != null){
            PageHelper.startPage(student.getPage(),student.getLimit());
        }
        return studentDao.queryStudentByTeacher(BeanMapUtils.beanToMap(student));
    }
    public List<Student> queryStudentByTeacher1(Integer teacherId,String stuName){
        Map<String, Object> map = MapParameter.getInstance()
                .add("teacherId", teacherId)
                .add("stuName", stuName)
                .getMap();
        return studentDao.queryStudentByTeacher1(map);
    }

    public List<Student> queryStudentByUser(String stuName) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("stuName", stuName)
                .getMap();
        return studentDao.queryStudentByUser(map);
    }

    public List<Student> queryListById(Integer id) {
        return studentDao.queryListById(id);
    }

    public List<Student> queryByClazzIdAndPoor(Integer clazzId,String flag) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("clazzId", clazzId)
                .add("flag", flag)
                .getMap();
        return studentDao.queryByClazzIdAndPoor(map);
    }

    public Student queryByClazzIdAndWork(Integer clazzId, Integer studentId) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("clazzId", clazzId)
                .add("studentId", studentId)
                .getMap();
        return studentDao.queryByClazzIdAndWork(map);
    }

}

package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.TeacherDao;
import com.ssm.entity.Teacher;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MD5Utils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class TeacherService {

    @Autowired
    private TeacherDao teacherDao;

    @Autowired
    private PowerService powerService;

    public int create(Teacher pi) {
        pi.setTeacherPwd(MD5Utils.getMD5(pi.getTeacherPwd()));
        return teacherDao.create(pi);
    }

    public int delete(Integer id) {
        return teacherDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = teacherDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Teacher teacher) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(teacher)).addId(teacher.getId()).getMap();
        return teacherDao.update(map);
    }

    public List<Teacher> query(Teacher teacher) {
        if(teacher != null && teacher.getPage() != null){
            PageHelper.startPage(teacher.getPage(),teacher.getLimit());
        }
        return teacherDao.query(BeanMapUtils.beanToMap(teacher));
    }

    public Teacher detail(Integer id) {
        return teacherDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Teacher teacher) {
        return teacherDao.count(BeanMapUtils.beanToMap(teacher));
    }

    public Teacher login(String userName, String password){
        Map<String, Object> map = MapParameter.getInstance()
                .add("teacherName", userName)
                .add("teacherPwd", password)
                .getMap();
        return teacherDao.detail(map);
    }


    public Teacher queryByTeacherId(Integer id) {
        return teacherDao.queryByTeacherId(id);
    }

    public Teacher queryByTeacherName(String name) {
        return teacherDao.queryByTeacherName(name);
    }

    public int updateAll(String ids) {
        int flag = 0;
        Integer power=1;
        for (String str : ids.split(",")) {
            flag = teacherDao.updateAll(MapParameter.getInstance().add("power",power).addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int updateAll1(String ids) {
        int flag = 0;
        Integer power=0;
        for (String str : ids.split(",")) {
            flag = teacherDao.updateAll(MapParameter.getInstance().add("power",power).addId(Integer.parseInt(str)).getMap());
            powerService.deletePower(Integer.parseInt(str));
        }
        return flag;
    }
}

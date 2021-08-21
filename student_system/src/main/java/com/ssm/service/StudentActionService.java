package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.StudentActionDao;
import com.ssm.entity.Image;
import com.ssm.entity.StudentAction;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentActionService {

    @Autowired
    private StudentActionDao studentActionDao;

    public int create(StudentAction pi) {
        return studentActionDao.create(pi);
    }

    public int delete(Integer id) {
        return studentActionDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studentActionDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(StudentAction studentAction) {
        return studentActionDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(studentAction)).addId(studentAction.getId()).getMap());
    }

    public List<StudentAction> query(StudentAction studyAction) {
        if(studyAction != null && studyAction.getPage() != null){
            PageHelper.startPage(studyAction.getPage(),studyAction.getLimit());
        }
        return studentActionDao.query(BeanMapUtils.beanToMap(studyAction));
    }

    public StudentAction detail(Integer id) {
        return studentActionDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(StudentAction studentAction) {
        return studentActionDao.count(BeanMapUtils.beanToMap(studentAction));
    }

    public int update(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studentActionDao.update(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update1(StudentAction studentAction) {
        return studentActionDao.update1(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(studentAction)).addId(studentAction.getId()).getMap());
    }

    public int refuse(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studentActionDao.refuse(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public StudentAction queryById(int id) {
        return studentActionDao.queryById(id);
    }

    public StudentAction queryByStuId(Integer stuId) {
        return studentActionDao.queryByStuId(stuId);
    }

    public StudentAction updateQueryById(Integer id) {
        return studentActionDao.updateQueryById(id);
    }
}

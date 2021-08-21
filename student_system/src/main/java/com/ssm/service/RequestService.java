package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.RequestDao;
import com.ssm.entity.Request;
import com.ssm.entity.Student;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestDao requestDao;
    @Autowired
    private TeacherCountService teacherCountService;

    public int create(Request pi) {
        return requestDao.create(pi);
    }

    public int delete(Integer id) {
        return requestDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = requestDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(String ids) {
        return requestDao.update(MapParameter.getInstance().addId(Integer.parseInt(ids)).getMap());
    }

    public List<Request> query(Request request) {
        if(request != null && request.getPage() != null){
            PageHelper.startPage(request.getPage(),request.getLimit());
        }
        return requestDao.query(BeanMapUtils.beanToMap(request));
    }

    public Request detail(Integer id) {
        return requestDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Request request) {
        return requestDao.count(BeanMapUtils.beanToMap(request));
    }

    public int check(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = requestDao.update(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update1(Request request) {
        return requestDao.update1(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(request)).addId(request.getId()).getMap());
    }


    public Request queryById(int parseInt) {
        return requestDao.queryById(parseInt);
    }

    public List<Request> userQuery(Request request) {
        if(request != null && request.getPage() != null){
            PageHelper.startPage(request.getPage(),request.getLimit());
        }
        return requestDao.userQuery(BeanMapUtils.beanToMap(request));
    }

    public int refuse(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = requestDao.refuse(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }


    public Request queryByStuId(Integer stuId) {
        return requestDao.queryByStuId(stuId);
    }

    public List<Request> studentQuery(Request request) {
        if(request != null && request.getPage() != null){
            PageHelper.startPage(request.getPage(),request.getLimit());
        }
        return requestDao.studentQuery(BeanMapUtils.beanToMap(request));
    }


    public Request updateQueryById(Integer id) {
        return requestDao.updateQueryById(id);
    }
}

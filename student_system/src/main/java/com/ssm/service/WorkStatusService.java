package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.WorkStatusDao;
import com.ssm.entity.Job;
import com.ssm.entity.WorkStatus;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class WorkStatusService {

    @Autowired
    WorkStatusDao workStatusDao;
    public List<WorkStatus> queryStudentWorkStatus(Integer studentId){
        return workStatusDao.queryStudentWorkStatus(studentId);
    }

    public List<WorkStatus> query(WorkStatus workStatus) {

        if(workStatus != null && workStatus.getPage() != null){
            PageHelper.startPage(workStatus.getPage(),workStatus.getLimit());
        }
        return workStatusDao.query(workStatus);
    }

    public List<WorkStatus> queryByStuId(Integer stuId) {

        return workStatusDao.queryByStuId(stuId);
    }

    public int create(WorkStatus pi) {
        return workStatusDao.create(pi);
    }

    public int delete(Integer id) {
        return workStatusDao.delete(MapParameter.getInstance().addId(id).getMap());
    }
    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = workStatusDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(WorkStatus workStatus) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(workStatus)).addId(workStatus.getId()).getMap();
        return workStatusDao.update(map);
    }


    public WorkStatus detail(Integer id) {
        return workStatusDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public WorkStatus detailByStudent(Integer stuId) {
        return workStatusDao.detail(MapParameter.getInstance().add("stuId",stuId).getMap());
    }

    public int count(WorkStatus workStatus) {
        return workStatusDao.count(BeanMapUtils.beanToMap(workStatus));
    }

}

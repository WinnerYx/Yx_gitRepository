package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.JobDao;
import com.ssm.entity.Image;
import com.ssm.entity.Job;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class JobService {

    @Autowired
    private JobDao jobDao;

    public int create(Job pi) {
        return jobDao.create(pi);
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = jobDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int updateJob(Job job) {
        Map<String, Object> map = MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(job)).addId(job.getId()).getMap();
        return jobDao.updateJob(map);
    }

    public List<Job> query(Job job) {
        if(job != null && job.getPage() != null){
            PageHelper.startPage(job.getPage(),job.getLimit());
        }
        return jobDao.query(BeanMapUtils.beanToMap(job));
    }

    public List<Job> queryJob(Job job) {
        return jobDao.queryJob(BeanMapUtils.beanToMap(job));
    }

    public Job detail(Integer id) {
        return jobDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public Job detailByStudent(Integer stuId) {
        return jobDao.detail(MapParameter.getInstance().addId(stuId).getMap());
    }

    public int count(Job job) {
        return jobDao.count(BeanMapUtils.beanToMap(job));
    }


    public Job queryJobById(Integer jobId) {
        return jobDao.queryJobById(jobId);
    }

    public int createJob(Job job) {
        return jobDao.createJob(job);
    }

    public int update(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = jobDao.update(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public List<Job> queryListJobStatus(Job job) {
        if(job != null && job.getPage() != null){
            PageHelper.startPage(job.getPage(),job.getLimit());
        }
        return jobDao.queryListJobStatus(BeanMapUtils.beanToMap(job));
    }

    public List<Job> queryRequestJob(Job job) {
        if(job != null && job.getPage() != null){
            PageHelper.startPage(job.getPage(),job.getLimit());
        }
        return jobDao.queryRequestJob(BeanMapUtils.beanToMap(job));
    }

    public Job queryById(int id) {
        return jobDao.queryById(id);
    }

    public int refuse(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = jobDao.refuse(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public Job queryByStuId(Integer stuId) {
        return jobDao.queryByStuId(stuId);
    }
}

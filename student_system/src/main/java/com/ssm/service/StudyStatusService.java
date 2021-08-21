package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.StudyStatusDao;
import com.ssm.entity.Image;
import com.ssm.entity.Request;
import com.ssm.entity.StudentAction;
import com.ssm.entity.StudyStatus;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudyStatusService {

    @Autowired
    private StudyStatusDao studyStatusDao;

    public int create(StudyStatus pi) {
        return studyStatusDao.create(pi);
    }

    public int delete(Integer id) {
        return studyStatusDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studyStatusDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(StudyStatus studyStatus) {
        return studyStatusDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(studyStatus)).addId(studyStatus.getId()).getMap());
    }
    public int update1(StudyStatus studyStatus) {
        return studyStatusDao.update1(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(studyStatus)).addId(studyStatus.getId()).getMap());
    }

    public List<StudyStatus> query(StudyStatus studyStatus) {
        if(studyStatus != null && studyStatus.getPage() != null){
            PageHelper.startPage(studyStatus.getPage(),studyStatus.getLimit());
        }
        return studyStatusDao.query(BeanMapUtils.beanToMap(studyStatus));
    }

    public StudyStatus detail(Integer id) {
        return studyStatusDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(StudyStatus studyStatus) {
        return studyStatusDao.count(BeanMapUtils.beanToMap(studyStatus));
    }

    public int update(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studyStatusDao.update(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int refuse(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = studyStatusDao.refuse(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public StudyStatus queryById(int id) {
        return studyStatusDao.queryById(id);
    }

    public StudyStatus queryByStuId(Integer stuId) {
        return studyStatusDao.queryByStuId(stuId);
    }

    public StudyStatus updateQueryById(Integer id) {
        return studyStatusDao.updateQueryById(id);
    }
}

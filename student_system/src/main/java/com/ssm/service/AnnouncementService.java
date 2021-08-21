package com.ssm.service;

import com.github.pagehelper.PageHelper;
import com.ssm.dao.AnnouncementDao;
import com.ssm.entity.Announcement;
import com.ssm.entity.Request;
import com.ssm.utils.BeanMapUtils;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnnouncementService {

    @Autowired
    private AnnouncementDao announcementDao;

    public int create(Announcement pi) {
        return announcementDao.create(pi);
    }

    public int delete(Integer id) {
        return announcementDao.delete(MapParameter.getInstance().addId(id).getMap());
    }

    public int delete(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = announcementDao.delete(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public int update(Announcement announcement) {
        return announcementDao.update(MapParameter.getInstance().add(BeanMapUtils.beanToMapForUpdate(announcement)).addId(announcement.getId()).getMap());
    }

    public List<Announcement> query(Announcement announcement) {
        if(announcement != null && announcement.getPage() != null){
            PageHelper.startPage(announcement.getPage(),announcement.getLimit());
        }
        return announcementDao.query(BeanMapUtils.beanToMap(announcement));
    }

    public Announcement detail(Integer id) {
        return announcementDao.detail(MapParameter.getInstance().addId(id).getMap());
    }

    public int count(Announcement announcement) {
        return announcementDao.count(BeanMapUtils.beanToMap(announcement));
    }

    public int update(String ids) {
        int flag = 0;
        for (String str : ids.split(",")) {
            flag = announcementDao.update(MapParameter.getInstance().addId(Integer.parseInt(str)).getMap());
        }
        return flag;
    }

    public List<Announcement> queryByResult1(String result1) {
        return announcementDao.queryByResult1(result1);
    }

    public Announcement queryById(int parseInt) {
        return announcementDao.queryById(parseInt);
    }

    public Announcement updateQueryById(Integer id) {
        return announcementDao.updateQueryById(id);
    }
}

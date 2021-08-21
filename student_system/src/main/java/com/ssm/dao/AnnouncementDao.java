package com.ssm.dao;

import com.ssm.entity.Announcement;
import com.ssm.entity.Request;

import java.util.List;
import java.util.Map;

public interface AnnouncementDao {
    public int create(Announcement pi);

    public int delete(Map<String, Object> paramMap);

    public int update(Map<String, Object> paramMap);

    public List<Announcement> query(Map<String, Object> paramMap);

    public Announcement detail(Map<String, Object> paramMap);

    public int count(Map<String, Object> paramMap);

    List<Announcement> queryByResult1(String result1);

    Announcement queryById(int parseInt);

    Announcement updateQueryById(Integer id);
}
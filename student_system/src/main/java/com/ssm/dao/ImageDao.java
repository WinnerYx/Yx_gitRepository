package com.ssm.dao;

import com.ssm.entity.Image;

import java.util.List;
import java.util.Map;

public interface ImageDao {
    public void save(Map<String, Object> map);

    public Image list(Integer sId);

    void update(Map<String, Object> map);

    void delete(int sId);

    Image queryByStuId(Integer stuId);
}

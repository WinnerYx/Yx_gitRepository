package com.ssm.service;

import com.ssm.dao.ImageDao;
import com.ssm.entity.Announcement;
import com.ssm.entity.Image;
import com.ssm.utils.MapParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ImageService {

    @Autowired
    private ImageDao imageDao;
    public void save(String image,Integer sId){
        Map<String, Object> map = MapParameter.getInstance()
                .add("image", image)
                .add("sId", sId)
                .getMap();
        imageDao.save(map);
    }

    public Image list(Integer sId){
        return imageDao.list(sId);
    }

    public void update(String imgurl, Integer sId) {
        Map<String, Object> map = MapParameter.getInstance()
                .add("imgurl", imgurl)
                .add("sId", sId)
                .getMap();
        imageDao.update(map);
    }

    public void delete(int sId) {
        imageDao.delete(sId);
    }

    public Image queryByStuId(Integer stuId) {
        return imageDao.queryByStuId(stuId);
    }
}

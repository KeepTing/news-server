package com.keeping.news.service.impl;

import com.keeping.news.dao.NewsUserMapper;
import com.keeping.news.model.NewsUser;
import com.keeping.news.service.NewsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/9.
 */
@Service
public class NewsUserServiceImpl implements NewsUserService {

    @Autowired
    NewsUserMapper newsUserMapper;
    @Override
    public NewsUser getById(int id) {
        return newsUserMapper.getById(id);
    }

    @Override
    public int addUser(NewsUser user) {
        return newsUserMapper.insert(user);
    }

    @Override
    public List<NewsUser> getListByMap(Map<String, Object> map) {
        return null;
    }

    @Override
    public List<NewsUser> getByPage(Map<String, Object> map, int pageIndex, int pageCount) {
        return newsUserMapper.getPageByMap(map,pageIndex,pageCount);
    }

    @Override
    public boolean isExsit(NewsUser user) {
        Map<String,Object> map=new HashMap<>();
        map.put("username",user.getUserName());
        List<NewsUser> list=newsUserMapper.getListByMap(map);
        if(list!=null && list.size()>0)
            return true;
        return false;
    }

    @Override
    public NewsUser login(NewsUser user) {
        Map<String ,Object> map=new HashMap<>();
        map.put("username",user.getUserName());
        map.put("password",user.getPassword());

        List<NewsUser> list=newsUserMapper.getListByMap(map);
        if(list!=null && list.size()>=0){
            return null;
        }
        return list.get(0);
    }

    @Override
    public boolean register(NewsUser user) {

        Map<String ,Object> map=new HashMap<>();
        map.put("username",user.getUserName());

        List<NewsUser> list=newsUserMapper.getListByMap(map);

        if(list!=null && list.size()>=0){  //用户存在
            return false;
        }

        newsUserMapper.insert(user);
        return true;
    }
}

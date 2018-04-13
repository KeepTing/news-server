package com.keeping.news.service;

import com.keeping.news.model.NewsUser;

import java.util.List;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/9.
 */
public interface NewsUserService {

    NewsUser getById(int id);

    int addUser(NewsUser user);

    List<NewsUser> getListByMap(Map<String,Object> map);

    List<NewsUser> getByPage(Map<String ,Object> map,int pageIndex,int pageCount);

    boolean isExsit(NewsUser user);

    NewsUser login(NewsUser user);
//
    boolean register(NewsUser user);
}

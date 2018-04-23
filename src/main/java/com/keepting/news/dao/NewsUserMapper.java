package com.keepting.news.dao;

import com.keepting.news.dao.provider.NewsUserProvider;
import com.keepting.news.model.NewsUser;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/9.
 */
@Mapper
@Transactional
public interface NewsUserMapper {


    @SelectProvider(type = NewsUserProvider.class,method = "getById")
    NewsUser getById(@Param("id") int id);

    @InsertProvider(type = NewsUserProvider.class,method = "insert")
    int insert(NewsUser user);

    @SelectProvider(type = NewsUserProvider.class,method = "getListByMap")
    List<NewsUser> getListByMap(Map<String ,Object> map);


    @SelectProvider(type = NewsUserProvider.class,method = "getPageByMap")
    List<NewsUser> getPageByMap(Map<String,Object> map,int pageIndex,int pageCount);

    @UpdateProvider(type = NewsUserProvider.class,method = "update")
    void update(NewsUser user);
}

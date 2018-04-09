package com.keeping.news.dao;

import com.keeping.news.dao.provider.NewsUserProvider;
import com.keeping.news.model.NewsUser;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.SecretKey;
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
    void insert(NewsUser user);

    @SelectProvider(type = NewsUserProvider.class,method = "getListByMap")
    List<NewsUser> getListByMap(Map<String ,Object> map);


    @SelectProvider(type = NewsUserProvider.class,method = "getPageByMap")
    List<NewsUser> getPageByMap(Map<String,Object> map,int pageIndex,int pageCount);
}

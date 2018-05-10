package com.keepting.news.dao;


import com.keepting.news.dao.provider.SpiderSiteProvider;
import com.keepting.news.model.SpiderSite;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by mayz on 2016/11/16.
 */
@Mapper
public interface SpiderSiteMapper {

    @SelectProvider(type = SpiderSiteProvider.class,method = "getById")
    SpiderSite getById(@Param("id") int id);

    @SelectProvider(type = SpiderSiteProvider.class,method = "getListByParam")
    List<SpiderSite> getListByParam(@Param("param") String param,@Param("value") Object value);

    @SelectProvider(type = SpiderSiteProvider.class,method = "getListByMap")
    List<SpiderSite> getListByMap(Map<String, Object> map);

    @UpdateProvider(type = SpiderSiteProvider.class,method = "update")
    int update(SpiderSite spiderSite);


    @SelectProvider(type = SpiderSiteProvider.class,method = "getPageList")
    List<SpiderSite> getPageList(Map<String,Object> map,@Param("pageIndex") int pageIndex, @Param("pageSize") int pageSize);

    @SelectProvider(type = SpiderSiteProvider.class,method = "count")
    int count(Map<String,Object> map);

    @InsertProvider(type = SpiderSiteProvider.class,method = "insert")
    void insert(SpiderSite site);
}

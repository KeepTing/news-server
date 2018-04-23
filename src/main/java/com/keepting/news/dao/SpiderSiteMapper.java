package com.keepting.news.dao;


import com.keepting.news.dao.provider.SpiderSiteProvider;
import com.keepting.news.model.SpiderSite;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by mayz on 2016/11/16.
 */
@Mapper
public interface SpiderSiteMapper {

    @SelectProvider(type = SpiderSiteProvider.class,method = "getById")
    SpiderSite getById(int id);

    @SelectProvider(type = SpiderSiteProvider.class,method = "getByParam")
    List<SpiderSite> getByParam(String param,Object value);

    @SelectProvider(type = SpiderSiteProvider.class,method = "getByMap")
    List<SpiderSite> getByMap(Map<String, Object> map);

    @UpdateProvider(type = SpiderSiteProvider.class,method = "update")
    int update(SpiderSite spiderSite);


    @SelectProvider(type = SpiderSiteProvider.class,method = "getPageList")
    List<SpiderSite> getPageList(Map<String,Object> map,int pageIndex, int pageSize);

    @SelectProvider(type = SpiderSiteProvider.class,method = "count")
    int count(Map<String,Object> map);

    @InsertProvider(type = SpiderSiteProvider.class,method = "insert")
    void insert(SpiderSite site);
}

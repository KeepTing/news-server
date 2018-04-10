package com.keeping.news.dao;

import com.keeping.news.dao.provider.ChannelProvider;
import com.keeping.news.model.Channel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/10.
 */
@Mapper
public interface ChannelMapper {

    @SelectProvider(type = ChannelProvider.class,method = "getById")
    Channel getById(int id);

    @SelectProvider(type = ChannelProvider.class,method = "getListByMap")
    List<Channel> getListByMap(Map<String,Object> map);

    @SelectProvider(type = ChannelProvider.class,method = "getAll")
    List<Channel> getAll();
}

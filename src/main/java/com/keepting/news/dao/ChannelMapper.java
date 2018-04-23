package com.keepting.news.dao;

import com.keepting.news.dao.provider.ChannelProvider;
import com.keepting.news.model.Channel;
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

    @SelectProvider(type = ChannelProvider.class,method = "getByName")
    Channel getByName(String  channelName);

    @SelectProvider(type = ChannelProvider.class,method = "getListByMap")
    List<Channel> getListByMap(Map<String,Object> map);

    @SelectProvider(type = ChannelProvider.class,method = "getAll")
    List<Channel> getAll();

    @SelectProvider(type = ChannelProvider.class,method = "getChannelByUserId")
    List<Channel> getChannelByUserId(int userId);


}

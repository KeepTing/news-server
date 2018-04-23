package com.keepting.news.service;

import com.keepting.news.model.Channel;

import java.util.List;

/**
 * Created by keepspy on 2018/4/10.
 */
public interface ChannelService {

    Channel getById(int id);

    List<Channel> getChannelsByUserId(int userId);

    List<Channel> getAll();

    Channel getByName(String channelName);
}

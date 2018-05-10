package com.keepting.news.service.impl;

import com.keepting.news.dao.ChannelMapper;
import com.keepting.news.dao.UserChannelMapper;
import com.keepting.news.model.Channel;
import com.keepting.news.model.UserChannel;
import com.keepting.news.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by keepspy on 2018/4/10.
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelMapper channelMapper;

    @Autowired
    UserChannelMapper userChannelMapper;

    @Override
    public Channel getById(int id) {
        return channelMapper.getById(id);
    }

    @Override
    public List<Channel> getChannelsByUserId(int userId) {
        return channelMapper.getChannelByUserId(userId);
    }

    @Override
    public List<Channel> getAll() {
        return channelMapper.getAll();
    }

    @Override
    public Channel getByName(String channelName) {
        return channelMapper.getByName(channelName);
    }
}

package com.keeping.news.service.impl;

import com.keeping.news.dao.ChannelMapper;
import com.keeping.news.model.Channel;
import com.keeping.news.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by keepspy on 2018/4/10.
 */
@Service
public class ChannelServiceImpl implements ChannelService {

    @Autowired
    ChannelMapper channelMapper;
    @Override
    public Channel getById(int id) {
        return channelMapper.getById(id);
    }
}

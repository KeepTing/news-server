package com.keepting.news.service.impl;

import com.keepting.news.dao.UserChannelMapper;
import com.keepting.news.model.UserChannel;
import com.keepting.news.service.UserChannelService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by keepspy on 2018/4/14.
 */
@Service
public class UserChannelServiceImpl implements UserChannelService {

    UserChannelMapper userChannelMapper;

    @Override
    public List<UserChannel> getByUserId(int userId) {
        return userChannelMapper.getByUserId(userId);
    }

    @Override
    public List<UserChannel>  getByChannelId(int channelId) {
        return userChannelMapper.getByChannelId(channelId);
    }

    @Override
    public void subChannel(UserChannel userChannel) {
        userChannelMapper.insert(userChannel);
    }

    @Override
    public void unSubChannel(UserChannel userChannel) {
        userChannelMapper.delete(userChannel);
    }
}

package com.keepting.news.service;

import com.keepting.news.model.UserChannel;

import java.util.List;

/**
 * Created by keepspy on 2018/4/14.
 */
public interface UserChannelService {

    List<UserChannel> getByUserId(int userId);

    List<UserChannel> getByChannelId(int channelId);

    void subChannel(UserChannel userChannel);

    void unSubChannel(UserChannel userChannel);
}

package com.keepting.news.dao;

import com.keepting.news.dao.provider.UserChannelProvider;
import com.keepting.news.model.UserChannel;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.SelectProvider;

import java.util.List;

/**
 * Created by keepspy on 2018/4/13.
 */
@Mapper
public interface UserChannelMapper {

    @SelectProvider(type = UserChannelProvider.class,method = "getByUserId")
    List<UserChannel> getByUserId(int userId);

    @SelectProvider(type = UserChannelProvider.class,method = "getByChannelId")
    List<UserChannel> getByChannelId(int channelId);

    @InsertProvider(type = UserChannelProvider.class,method = "insert")
    void insert(UserChannel userChannel);

    @DeleteProvider(type = UserChannelProvider.class,method = "delete")
    void delete(UserChannel userChannel);

}

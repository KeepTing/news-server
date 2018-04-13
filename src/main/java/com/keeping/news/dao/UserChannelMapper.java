package com.keeping.news.dao;

import com.keeping.news.dao.provider.UserChannelProvider;
import com.keeping.news.model.UserChannel;
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
    List<UserChannel> getByChannelId(int userId);

}

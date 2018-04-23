package com.keepting.news.dao;

import com.keepting.news.dao.provider.CommentProvider;
import com.keepting.news.model.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/14.
 */
@Mapper
public interface CommentMapper {

    @SelectProvider(type = CommentProvider.class,method = "getById")
    Comment getById(int id);

    @SelectProvider(type = CommentProvider.class,method = "getByParam")
    List<Comment> getByParam(String param, Object value);

    @SelectProvider(type = CommentProvider.class,method = "getListByMap")
    List<Comment> getListByMap(Map<String,Object> map);

    @InsertProvider(type = CommentProvider.class,method = "insert")
    void insert(Comment comment);

    @UpdateProvider(type = CommentProvider.class,method = "update")
    void update(Comment comment);
}

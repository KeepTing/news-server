package com.keepting.news.service;

import com.keepting.news.model.Comment;

import java.util.List;

/**
 * Created by keepspy on 2018/4/14.
 */
public interface CommentService {

    Comment getById(int id);

    List<Comment> getListByParam(String param, Object value);

    void addComment(Comment comment);

    void update(Comment comment);


}

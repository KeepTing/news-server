package com.keepting.news.service.impl;

import com.keepting.news.dao.CommentMapper;
import com.keepting.news.model.Comment;
import com.keepting.news.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by keepspy on 2018/4/14.
 */
@Service
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;


    @Override
    public Comment getById(int id) {
        return null;
    }

    @Override
    public List<Comment> getListByParam(String param, Object value) {
        return commentMapper.getByParam(param,value);
    }

    @Override
    public void addComment(Comment comment) {
        commentMapper.insert(comment);
    }

    @Override
    public void update(Comment comment) {
        commentMapper.update(comment);
    }
}

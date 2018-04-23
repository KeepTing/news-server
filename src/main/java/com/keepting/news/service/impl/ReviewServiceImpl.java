package com.keepting.news.service.impl;

import com.keepting.news.dao.ReviewMapper;
import com.keepting.news.model.Review;
import com.keepting.news.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by keepspy on 2018/4/14.
 */
@Service
public class ReviewServiceImpl implements ReviewService {

    @Autowired
    ReviewMapper reviewMapper;


    @Override
    public Review getById(int id) {
        return null;
    }

    @Override
    public List<Review> getListByParam(String param, Object value) {
        return reviewMapper.getByParam(param,value);
    }

    @Override
    public void addReview(Review review) {
        reviewMapper.insert(review);
    }

    @Override
    public void update(Review review) {
        reviewMapper.update(review);
    }
}

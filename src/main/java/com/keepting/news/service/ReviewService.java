package com.keepting.news.service;

import com.keepting.news.model.Review;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * Created by keepspy on 2018/4/14.
 */
public interface ReviewService {

    Review getById(int id);

    List<Review> getListByParam(String param, Object value);

    void addReview(Review review);

    void update(Review review);


}

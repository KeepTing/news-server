package com.keepting.news.dao;

import com.keepting.news.dao.provider.ReviewProvider;
import com.keepting.news.model.Review;
import org.apache.ibatis.annotations.*;
import org.bson.types.ObjectId;

import java.util.List;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/14.
 */
@Mapper
public interface ReviewMapper {

    @SelectProvider(type = ReviewProvider.class,method = "getById")
    Review getById(int id);

    @SelectProvider(type = ReviewProvider.class,method = "getByParam")
    List<Review> getByParam(String param, Object value);

    @SelectProvider(type = ReviewProvider.class,method = "getListByMap")
    List<Review> getListByMap(Map<String,Object> map);

    @InsertProvider(type = ReviewProvider.class,method = "insert")
    void insert(Review review);

    @UpdateProvider(type = ReviewProvider.class,method = "update")
    void update(Review review);
}

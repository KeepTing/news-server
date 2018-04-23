package com.keepting.news.dao;

import com.keepting.news.model.SpiderArticle;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * Created by liuwei1 on 2016/12/5.
 */
public interface SpiderArticleDao {

    SpiderArticle getById(ObjectId id);
    SpiderArticle selectOne(Query query) throws Exception;
    List<SpiderArticle> selectList(Query query) throws Exception;
    long count(Query query) throws Exception;
}

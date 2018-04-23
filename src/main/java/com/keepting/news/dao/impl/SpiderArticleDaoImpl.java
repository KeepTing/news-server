package com.keepting.news.dao.impl;

import com.keepting.news.dao.SpiderArticleDao;
import com.keepting.news.model.SpiderArticle;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by liuwei1 on 2016/12/5.
 */
@Repository("spiderArticleDao")
@Transactional(propagation = Propagation.REQUIRED,isolation = Isolation.READ_COMMITTED)
public class SpiderArticleDaoImpl implements SpiderArticleDao {

    @Qualifier("mongoClient")
    @Autowired
    private MongoTemplate mongoClient;

    public SpiderArticle selectOne(Query query) throws Exception {
        return mongoClient.findOne(query,SpiderArticle.class);
    }

    @Override
    public SpiderArticle getById(ObjectId id) {
        return mongoClient.findById(id,SpiderArticle.class);
    }

    @Override
    public List<SpiderArticle> selectList(Query query) throws Exception {
        return mongoClient.find(query,SpiderArticle.class);
    }

    @Override
    public long count(Query query) throws Exception {
        return mongoClient.count(query,SpiderArticle.class);
    }
}

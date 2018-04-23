package com.keepting.news.service.impl;

import com.keepting.news.dao.SpiderArticleDao;
import com.keepting.news.model.SpiderArticle;
import com.keepting.news.service.SpiderArticleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liuwei1 on 2016/12/5.
 */
@Repository("articleService")
public class SpiderArticleServiceImpl implements SpiderArticleService {

    @Autowired
    private SpiderArticleDao spiderArticleDao;

    @Override
    public SpiderArticle selectOne(Query query) throws Exception {
        return spiderArticleDao.selectOne(query);
    }

    @Override
    public List<SpiderArticle> selectList(Query query) throws Exception {
        return spiderArticleDao.selectList(query);
    }

    @Override
    public long count(Query query) throws Exception {
        return spiderArticleDao.count(query);
    }

    @Override
    public SpiderArticle getById(ObjectId id) {
        return spiderArticleDao.getById(id);
    }
}

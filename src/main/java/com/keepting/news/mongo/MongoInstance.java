package com.keepting.news.mongo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author lijie7
 * @date 2018/4/8
 * @Description
 * @modified By
 */
@Component
public class MongoInstance {

    @Qualifier("mongoFactory")
    @Resource
    private MongoDbFactory mongoDbFactory;

    public @Bean(value = "mongoClient")
    MongoTemplate mongoClientInstance() {
        MongoTemplate template = new MongoTemplate(mongoDbFactory);
        return template;
    }

}

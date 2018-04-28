package com.keepting.news.dao.provider;

import com.keepting.news.model.NewsUser;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/9.
 */
public class NewsUserProvider {


    String table="news_user";  //用户表名

    public String getById(int id){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table).WHERE("id = "+id);
        return sql.toString();
    }

    public String insert(NewsUser user){
        return new SQL(){{
            INSERT_INTO(table)
                    .VALUES("username","#{userName}")
                    .VALUES("password","#{password}")
                    .VALUES("realname","#{realName}")
                    .VALUES("createTime",new SimpleDateFormat("yyyy-MM-dd HH:mm;ss").format(new Date()));
        }}.toString();
    }

    public String getListByMap(Map<String,Object> map){

        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        map.forEach((key,value)->{
            sql.WHERE(key+" = "+value);
        });
        System.out.println(sql.toString());
        return  sql.toString();
    }

    public String getPageByMap(Map<String ,Object> map,int pageIndex,int pageSize){
        String sql=getListByMap(map);
        return sql+"limit "+pageIndex*pageSize+" , "+pageSize;
    }

    public String update(NewsUser user){
        return new SQL().UPDATE(table)
                .SET("userName=#{userName}")
                .SET("password=#{password}")
                .SET("realName=#{realName}")
                .SET("score=#{score}")
                .WHERE("id=#{id}").toString();
    }
}

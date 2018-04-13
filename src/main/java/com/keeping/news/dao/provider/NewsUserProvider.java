package com.keeping.news.dao.provider;

import com.keeping.news.model.NewsUser;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/9.
 */
public class NewsUserProvider {

    String db="news_user";  //用户表名

    public String getById(int id){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(db).WHERE("id = "+id);
        return sql.toString();
    }

    public String insert(NewsUser user){
        return new SQL(){{
            INSERT_INTO(db)
                    .VALUES("username","#{userName}")
                    .VALUES("password","#{password}")
                    .VALUES("realname","#{realName}")
                    .VALUES("createTime",new SimpleDateFormat("yyyy-MM-dd HH:mm;ss").format(new Date()));
        }}.toString();
    }

    public String getListByMap(Map<String,Object> map){

       SQL sql=new SQL();
        sql.SELECT("*").FROM(db);
        map.forEach((key,value)->{
            sql.WHERE(key+" = "+value);
        });
        return  sql.toString();
    }

    public String getPageByMap(Map<String ,Object> map,int pageIndex,int pageCount){
        String sql=getListByMap(map);
        return sql+"limit "+pageIndex+" , "+pageCount;
    }
}

package com.keeping.news.dao.provider;

import com.keeping.news.model.NewsUser;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

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
        SQL sql=new SQL();
        sql.INSERT_INTO(db);
        if(user.getUserName()!=null && !user.getUserName().equals("")){
            sql.VALUES("username",user.getUserName());
        }
        if(user.getRealName()!=null && !user.getRealName().equals("")){
            sql.VALUES("realname",user.getRealName());
        }
        if(user.getPassword()!=null && !user.getPassword().equals("")){
            sql.VALUES("password",user.getPassword());
        }
        if(user.getChannels()!=null && !user.getChannels().equals("")){
            sql.VALUES("chanels",user.getChannels());
        }
        sql.VALUES("createTime",user.getCreateTime());

        return sql.toString();
    }

    public String getListByMap(Map<String,Object> map){
       SQL sql=new SQL();
        sql.SELECT("*").FROM(db);

        for(String param : map.keySet()){
            sql.WHERE(param+" = " +map.get(param));
        }

        return  sql.toString();
    }

    public String getPageByMap(Map<String ,Object> map,int pageIndex,int pageCount){
        String sql=getListByMap(map);
        return sql+"limit "+pageIndex+" , "+pageCount;
    }
}

package com.keepting.news.dao.provider;

import com.keepting.news.model.Comment;
import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by keepspy on 2018/4/14.
 */
public class CommentProvider {
    String table="comment";

    public String getById(int id){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table)
        .WHERE("id = #{id}");
        return sql.toString();
    }

    public String getByParam(String param, Object value){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        if(param.equals("id") ||param.equals("user_id") || param.equals("likes") ||param.equals("status"))
                    sql.WHERE(param+" = "+value);
        else
            sql.WHERE(param+" = '"+value+"'");
        sql.ORDER_BY("createTime desc");
        return sql.toString();
    }

    public String getListByMap(Map<String,Object> map){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        map.forEach((key,value)->{
            if(key.equals("id") || key.equals("user_id") || key.equals("likes") || key.equals("status"))
                sql.WHERE(key+" = "+value);
            else
                sql.WHERE(key+" = '"+value+"'");
        });

        return sql.toString();
    }

    public String insert(Comment comment){
        SQL sql=new SQL();
        sql.INSERT_INTO(table)
                .VALUES("content","#{content}")
                .VALUES("user_id","#{user_id}")
                .VALUES("article_id","#{article_id}")
                .VALUES("createTime","#{createTime}")
                .VALUES("status","0");
        return  sql.toString();
    }

    public String update(Comment comment){
        return new SQL().UPDATE(table)
                .SET("likes=#{likes}")
                .SET("status=#{status}")
                .SET("report=#{report}")
                .WHERE("id=#{id}").toString();
    }

}

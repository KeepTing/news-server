package com.keepting.news.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by keepspy on 2018/4/10.
 */
public class ChannelProvider {

    String table="channel";

    public String getById(int id) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(table).WHERE("id=" + id);
        return sql.toString();
    }

    public String getByName(String channelName) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(table).WHERE("channel_name= #{channelName}");
        return sql.toString();
    }

    public String getListByMap(Map<String,Object> map){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        map.forEach((key,value)->{
            if(key.equals("id"))
                sql.WHERE(key+" = "+value);
            sql.WHERE(key+" = '"+value+"'");
        });
        return sql.toString();
    }

    public String getAll(){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        return sql.toString();
    }

    public String getChannelByUserId(int userId){
       return "SELECT DISTINCT ch.* from news_user u ,channel ch ,user_channel uc" +
                        " WHERE u.id=uc.user_id and ch.id=uc.channel_id and u.id="+userId;
    }

}

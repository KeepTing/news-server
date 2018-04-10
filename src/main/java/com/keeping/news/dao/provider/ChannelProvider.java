package com.keeping.news.dao.provider;

import org.apache.ibatis.jdbc.SQL;

import java.util.Map;

/**
 * Created by keepspy on 2018/4/10.
 */
public class ChannelProvider {

    String db="channel";

    public String getById(int id) {
        SQL sql = new SQL();
        sql.SELECT("*").FROM(db).WHERE("id=" + id);
        return sql.toString();
    }

    public String getListByMap(Map<String,Object> map){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(db);
        for(String param : map.keySet()){
            sql.WHERE(param+" = "+map.get(param));
        }

        return sql.toString();
    }

    public String getAll(){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(db);
        return sql.toString();
    }

}

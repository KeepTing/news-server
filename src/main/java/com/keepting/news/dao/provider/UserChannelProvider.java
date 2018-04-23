package com.keepting.news.dao.provider;

import com.keepting.news.model.UserChannel;
import org.apache.ibatis.jdbc.SQL;

/**
 * Created by keepspy on 2018/4/13.
 */
public class UserChannelProvider {
    private String table="user_channel";

    public String getByUserId(int userId){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table).WHERE("user_id = #{userId}");
        return  sql.toString();
    }

    public String getByChannelId(int channelId){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table).WHERE("channel_id = #{channelId}");
        return  sql.toString();
    }


    public String insert(UserChannel userChannel){
        SQL sql=new SQL();
        sql.INSERT_INTO(table)
                .VALUES("user_id","#{user_id}")
                .VALUES("channel_id","#{channel_id}");

        return sql.toString();
    }

    public String delete(UserChannel userChannel){
        SQL sql=new SQL();
        sql.DELETE_FROM(table)
                .WHERE("user_id = #{user_id}")
                .WHERE("channel_id = #{channel_id}");
        return sql.toString();
    }
}

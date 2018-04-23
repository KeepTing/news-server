package com.keepting.news.dao.provider;

import com.keepting.news.model.SpiderSite;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.jdbc.SQL;
import org.springframework.beans.factory.annotation.Value;

import java.util.Map;

/**
 * Created by keepspy on 2018/4/13.
 */
@Mapper
public class SpiderSiteProvider {
    @Value("mysql.table.spiderSite")
    String table;

    public String getById(int id){
        return new SQL(){{
            SELECT("*").FROM(table).WHERE("id = #{id}");
        }}.toString();
    }

    public String getByParam(String param,Object value){
        return new SQL(){{
            SELECT("*").FROM(table).WHERE("#{param} = #{value}");
        }}.toString();
    }


    public String getByMap(Map<String,Object> map){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        map.forEach((key,value)->{sql.WHERE(key+" = "+value);});
        return  sql.toString();
    }

    public String update(SpiderSite spiderSite){
        return new SQL(){{
            UPDATE(table).SET("linkUrl=#{linkUrl}")
                    .SET("mediaName=#{mediaName}")
                    .SET("site_type=#{site_type}")
                    .SET("hub_role=#{hub_role}")
                    .SET("role_type=#{role_type}")
                    .SET("detailContent=#{detailContent}")
                    .SET("detailDate=#{detailDate}")
                    .SET("detailTitle=#{detailTitle}")
                    .SET("detailFrom=#{detailFrom}")
                    .SET("detailAbstract=#{detailAbstract}")
                    .SET("total_link=#{total_link}")
                    .SET("crawl_link=#{crawl_link}")
                    .SET("crawlResult=#{crawlResult}")
                    .SET("phantomjs=#{phantomjs}")
                    .SET("channel=#{channel}")
                    .SET("cycle=#{cycle}")
                    .SET("listAreaRule=#{listAreaRule}")
                    .SET("urlReplace=#{urlReplace}")
                    .SET("status=#{status}")
                    .WHERE("id=#{id}");
        }}.toString();
    }

    public String getPageList(Map<String,Object> map,int pageIndex,int pageCount){
        SQL sql=new SQL();
        sql.SELECT("*").FROM(table);
        map.forEach((key,value)->{sql.WHERE(key+" = "+value);});
        return sql.toString()+" limit #{pageIndex},#{pageCount}";
    }

    public String count(Map<String,Object> map){
        SQL sql=new SQL();
        sql.SELECT("count(*)").FROM(table);
        map.forEach((key,value)->{sql.WHERE(key+" = "+value);});
        return sql.toString();
    }

    public String insert(SpiderSite spiderSite){
        return new SQL(){{
            INSERT_INTO(table)
                    .VALUES("mediaName","#{mediaName}")
                    .VALUES("site_type","#{site_type}")
                    .VALUES("hub_role","#{hub_role}")
                    .VALUES("role_type","#{role_type}")
                    .VALUES("detailContent","#{detailContent}")
                    .VALUES("detailDate","#{detailDate}")
                    .VALUES("detailTitle","#{detailTitle}")
                    .VALUES("detailFrom","#{detailFrom}")
                    .VALUES("detailAbstract","#{detailAbstract}")
                    .VALUES("total_link","#{total_link}")
                    .VALUES("crawl_link","#{crawl_link}")
                    .VALUES("crawlResult","#{crawlResult}")
                    .VALUES("phantomjs","#{phantomjs}")
                    .VALUES("channel","#{channel}")
                    .VALUES("cycle","#{cycle}")
                    .VALUES("listAreaRule","#{listAreaRule}")
                    .VALUES("urlReplace","#{urlReplace}")
                    .VALUES("status","#{status}")
                    .VALUES("createTime","#{createTime}");
        }}.toString();
    }
}

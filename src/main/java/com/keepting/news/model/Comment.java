package com.keepting.news.model;

import org.bson.types.ObjectId;

/**
 * Created by keepspy on 2018/4/14.
 */
public class Comment {
    private int id;
    private int user_id;  //用户id
    private ObjectId article_id; //文章id
    private int likes;
    private String content;  //评论内容
    private int is_report;  //是否被举报
    private String report;  //举报内容
    private String createTime ; //创建时间

    public Comment(String content, int user_id, ObjectId article_id, int likes, int is_report, String report, String createTime) {
        this.content = content;
        this.user_id = user_id;
        this.likes=likes;
        this.article_id=article_id;
        this.is_report = is_report;
        this.report = report;
        this.createTime=createTime;
    }

    public Comment() {
    }

    public ObjectId getArticle_id() {
        return article_id;
    }

    public void setArticle_id(ObjectId article_id) {
        this.article_id = article_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public int getLikes() {
        return likes;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getIs_report() {
        return is_report;
    }

    public void setIs_report(int is_report) {
        this.is_report = is_report;
    }

    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateTime() {
        return createTime;
    }
}

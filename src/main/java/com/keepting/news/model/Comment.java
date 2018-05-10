package com.keepting.news.model;

import org.bson.types.ObjectId;

/**
 * Created by keepspy on 2018/4/14.
 */
public class Comment {
    private int id;
    private int user_id;  //用户id
    private String article_id; //文章id
    private int likes;
    private String content;  //评论内容
    private int status;  //状态（0：正常 1：举报）
    private String report;  //举报内容
    private String createTime ; //创建时间

    public Comment(String content, int user_id, String article_id, int likes, int status, String report, String createTime) {
        this.content = content;
        this.user_id = user_id;
        this.likes=likes;
        this.article_id=article_id;
        this.status = status;
        this.report = report;
        this.createTime=createTime;
    }

    public Comment() {
    }


    public void setArticle_id(String article_id) {
        this.article_id = article_id;
    }

    public String getArticle_id() {
        return article_id;
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

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStatus() {
        return status;
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

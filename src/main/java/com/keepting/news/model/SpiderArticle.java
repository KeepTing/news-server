package com.keepting.news.model;


import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by liuwei1 on 2016/12/5.
 */
@Document(collection = "spider_article_2018")
public class SpiderArticle implements Serializable {

    private ObjectId _id;
//    标题
    private String title;
//    内容
    private String content;
//    频道
    private String channel;
//    链接
    private String sourceLink;
//
    private String sourceAlias;
//   发表时间
    private String publishedTime;
//    创建时间
    private Long createTime;
    private String catid;
//    来源
    private String from;
//    摘要
    private String abstracts;
//    作者
    private String author;
    private int dataSource;
    private String upId;
//    状态（0:下线；1:上线；2:待讨论）
    private int status;

    @Override
    public String toString() {
        return "SpiderNotice{" +
                "_id='" + _id + '\'' +
                ",title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", channel='" + channel + '\'' +
                ", sourceLink='" + sourceLink + '\'' +
                ", sourceAlias='" + sourceAlias + '\'' +
                ", publishedTime='" + publishedTime + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", catid='" + catid + '\'' +
                ", from='" + from + '\'' +
                ", abstracts='" + abstracts + '\'' +
                ", author='" + author + '\'' +
                '}';
    }


    public Object get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
    }

    public String getCatid() {
        return catid;
    }

    public void setCatid(String catid) {
        this.catid = catid;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getAbstracts() {
        return abstracts;
    }

    public void setAbstracts(String abstracts) {
        this.abstracts = abstracts;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getDataSource() {
        return dataSource;
    }

    public void setDataSource(int dataSource) {
        this.dataSource = dataSource;
    }

    public String getUpId() {
        return upId;
    }

    public void setUpId(String upId) {
        this.upId = upId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSourceLink() {
        return sourceLink;
    }

    public void setSourceLink(String sourceLink) {
        this.sourceLink = sourceLink;
    }

    public String getSourceAlias() {
        return sourceAlias;
    }

    public void setSourceAlias(String sourceAlias) {
        this.sourceAlias = sourceAlias;
    }

    public String getPublishedTime() {
        return publishedTime;
    }

    public void setPublishedTime(String publishedTime) {
        this.publishedTime = publishedTime;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

}

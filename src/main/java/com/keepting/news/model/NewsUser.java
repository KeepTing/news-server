package com.keepting.news.model;



/**
 * Created by keepspy on 2018/4/9.
 */

public class NewsUser {

    private int id;
    private String userName;
    private String realName;
    private String password;
    private  String createTime;
    private String headImg;
    private int score;


    public NewsUser(int id,String headImg, String userName, String realName, String password,int score, String createTime) {
        this.id = id;
        this.userName = userName;
        this.headImg=headImg;
        this.realName = realName;
        this.password = password;
        this.score=score;
        this.createTime = createTime;
    }

    public NewsUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setHeadImg(String headImg) {
        this.headImg = headImg;
    }

    public String getHeadImg() {
        return headImg;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

}

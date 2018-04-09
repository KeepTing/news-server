package com.keeping.news.model;



/**
 * Created by keepspy on 2018/4/9.
 */

public class NewsUser {

    private int id;
    private String userName;
    private String realName;
    private String password;
    private  String createTime;
    private String channels;


    public NewsUser(int id, String userName, String realName, String password, String createTime, String channels) {
        this.id = id;
        this.userName = userName;
        this.realName = realName;
        this.password = password;
        this.createTime = createTime;
        this.channels = channels;
    }

    public NewsUser() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getChannels() {
        return channels;
    }

    public void setChannels(String channels) {
        this.channels = channels;
    }


    @Override
    public String toString() {
        return this.getUserName()+" \n"+this.getRealName()+"\n"+this.getPassword();
    }
}

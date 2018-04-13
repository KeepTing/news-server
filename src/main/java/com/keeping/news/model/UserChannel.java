package com.keeping.news.model;

/**
 * Created by keepspy on 2018/4/13.
 */
public class UserChannel {

    private int id;
    private int user_id;
    private int channel_id;

    public UserChannel(int id, int user_id, int channel_id) {
        this.id = id;
        this.user_id = user_id;
        this.channel_id = channel_id;
    }

    public UserChannel() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getChannel_id() {
        return channel_id;
    }

    public void setChannel_id(int channel_id) {
        this.channel_id = channel_id;
    }
}

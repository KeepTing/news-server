package com.keepting.news.model;

/**
 * Created by keepspy on 2018/4/10.
 */
public class Channel {
    private int id;
    private String channel_name;

    public Channel() {
    }

    public Channel(int id, String channel_name) {
        this.id = id;
        this.channel_name = channel_name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setChannel_name(String channel_name) {
        this.channel_name = channel_name;
    }

    public String getChannel_name() {
        return channel_name;
    }
}

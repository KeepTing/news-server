package com.keeping.news.model;

/**
 * Created by keepspy on 2018/4/10.
 */
public class Channel {
    private int id;
    private String channelName;

    public Channel() {
    }

    public Channel(int id, String channelName) {
        this.id = id;
        this.channelName = channelName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }
}

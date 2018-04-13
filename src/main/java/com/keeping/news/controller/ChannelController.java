package com.keeping.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.keeping.news.model.Channel;
import com.keeping.news.model.NewsUser;
import com.keeping.news.service.ChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by keepspy on 2018/4/10.
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {

//    @Autowired
//    ChannelService channelService;


    @RequestMapping("/index")
    public String getUser(HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user");
        if(user==null){
            return "";
        }

        String channels=user.getChannels();
//        String
        return "true";
    }

    @PostMapping("/subscribe")
    public String subscribe(@ModelAttribute Channel channel,HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user");
        if(user==null){  //用户未登录
            return "no_login";
        }

        return "true";
    }

}

package com.keepting.news.controller;

import com.keepting.news.model.Channel;
import com.keepting.news.model.NewsUser;
import com.keepting.news.model.UserChannel;
import com.keepting.news.service.ChannelService;
import com.keepting.news.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Created by keepspy on 2018/4/10.
 */
@RestController
@RequestMapping("/channel")
public class ChannelController {

    @Autowired
    ChannelService channelService;

    @Autowired
    UserChannelService userChannelService;


    @RequestMapping("/index")
    public String getUser(HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user");
        if(user==null){
            return "";
        }

//        String
        return "true";
    }

    /**
     * 订阅频道
     * @param channelName
     * @param session
     * @return
     */
    @PostMapping("/sub/{channelName}")
    public String subscribe(@PathVariable String channelName, HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user");
        if(user==null){  //用户未登录
            return "no_login";
        }

        Channel channel=channelService.getByName(channelName); //通过频道名获取频道

        UserChannel userChannel=new UserChannel(user.getId(),channel.getId());

//        添加用户-频道表记录
        userChannelService.subChannel(userChannel);

        return "success";
    }


    /**
     * 取消订阅频道
     * @param channelName
     * @param session
     * @return
     */
    @PostMapping("/unSub/{channelName}")
    public String unSubScribe(@PathVariable String channelName,HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user");
        if(user==null){  //用户未登录
            return "no_login";
        }

        //通过频道名获取频道信息
        Channel channel=channelService.getByName(channelName);

        UserChannel userChannel=new UserChannel(user.getId(),channel.getId());

//        删除用户-频道表记录
        userChannelService.unSubChannel(userChannel);
        return "success";
    }
}

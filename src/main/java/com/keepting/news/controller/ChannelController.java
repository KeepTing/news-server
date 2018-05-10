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
     * @param session
     * @return
     */
    @PostMapping(value = "/edit",headers = "Accept=application/json", produces = "application/json;charset=UTF-8")
    public String subscribe(@RequestParam(value = "channelIds",required = false) String channelIds,
                            @RequestParam(value = "unChannelIds",required = false) String unChannelIds,
                            HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("user");
        if(user==null){  //用户未登录
            return "no_login";
//            user=new NewsUser();
//            user.setId(1);
        }

        String[] ids=channelIds.split(",");
        String[] unIds=unChannelIds.split(",");
        if(ids.length<=0 && unIds.length<=0)
            return "no_change";

        if(ids.length>0){
            //添加订阅的频道
            for(int i=0;i<ids.length;i++){
                if(!ids[i].equals("")){
                    int channelId=Integer.parseInt(ids[i]);
                    Channel channel=channelService.getById(channelId); //通过频道名获取频道
                    if(channel!=null){
                        UserChannel userChannel=new UserChannel(user.getId(),channel.getId());
                        //添加用户-频道表记录
                        userChannelService.subChannel(userChannel);
                    }
                }

            }
        }



        //删除要取消订阅的频道
        if(!"".equals(unIds) && unIds.length>0){
            for(int i=0;i<unIds.length;i++){
                if(!unIds[i].equals("")){
                    int unChannelId=Integer.parseInt(unIds[i]);
                    Channel channel=channelService.getById(unChannelId); //通过频道名获取频道
                    if(channel!=null){
                        UserChannel userChannel=new UserChannel(user.getId(),channel.getId());
                        //添加用户-频道表记录
                        userChannelService.unSubChannel(userChannel);
                    }
                }

            }
        }


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

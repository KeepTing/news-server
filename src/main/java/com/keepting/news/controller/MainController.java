package com.keepting.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.keepting.news.model.Channel;
import com.keepting.news.model.NewsUser;
import com.keepting.news.model.SpiderArticle;
import com.keepting.news.model.UserChannel;
import com.keepting.news.mongo.SpringDataPageable;
import com.keepting.news.service.ChannelService;
import com.keepting.news.service.SpiderArticleService;
import com.keepting.news.service.UserChannelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by keepspy on 2018/4/10.
 */
@RestController
public class MainController {

    int pageSize=20;
    int pageCount;

    @Autowired
    SpiderArticleService articleService;

    @Autowired
    ChannelService channelService;

    /**
     * 新闻系统首页
     * @param request
     * @param session
     * @return 用户订阅的频道、未订阅的频道和推荐类文章列表json
     */
    @GetMapping("/index/{channelName}")
    public String index(@PathVariable(value = "channelName",required = false) String channelName, HttpServletRequest request, HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user");
        Map<String,Object> map=new HashMap<>();
        List<Channel> channels=new ArrayList<>();  //频道列表，用户不登录为空
        List<Channel> unChannels=channelService.getAll(); //未订阅频道列表
        List<SpiderArticle> articles;  //文章列表

        if(user!=null){  //用户已登录
            channels=channelService.getChannelsByUserId(user.getId());  //订阅的频道列表
            channels.forEach(channel -> {
                unChannels.remove(channel); //去掉已订阅频道列表
            });
        }

        int startPage=0;
        if(request.getParameter("page")!=null){
            startPage = Integer.parseInt(request.getParameter("page"));
        }
        int total= 0;
        try {
            Query query=new Query();

            //如果频道名不为空，说明用户是通过点击频道链接获取文章列表
            if(channelName!=null && !channelName.equals("")){
                query.addCriteria(Criteria.where("channel").is(channelName));
            }
            total = (int) articleService.count(query);
            pageCount=total%pageSize==0?total/pageSize:total/pageSize+1;  //最大页数
            startPage=startPage>pageCount?pageCount:startPage;  //不能超过最大页
            SpringDataPageable pageable=new SpringDataPageable();
//            Query query=new Query();
            pageable.setPagenumber(startPage);
            pageable.setPagesize(pageSize);
            pageable.setSort(new Sort(Sort.Order.desc("createTime")));

            query.with(pageable);
            articles=articleService.selectList(query);

            map.put("channels",channels);
            map.put("unChannels",unChannels);
            map.put("articles",articles);

            return JSONObject.toJSONString(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }
}

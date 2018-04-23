package com.keepting.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.keepting.news.model.Channel;
import com.keepting.news.model.NewsUser;
import com.keepting.news.model.Review;
import com.keepting.news.model.SpiderArticle;
import com.keepting.news.mongo.SpringDataPageable;
import com.keepting.news.service.ReviewService;
import com.keepting.news.service.SpiderArticleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.*;

import javax.management.Query;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by keepspy on 2018/4/9.
 */
@RestController
@RequestMapping("/aticle")
public class ArticleController {

    @Autowired
    SpiderArticleService articleService;

    @Autowired
    ReviewService reviewService;

    /**
     * 根据文章id获取文章页面信息，包括文章信息，最新评论，最热评论
     * @param id
     * @param session
     * @return
     */
    @RequestMapping("detail/{id}")
    public String detail(@PathVariable String id , HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user");
        if(user==null){
            return "no_login";
        }

        Map<String,Object> map=new HashMap<>();
        ObjectId objectId=new ObjectId(id);
        SpiderArticle article=articleService.getById(objectId);

        //根据article_id获取此篇文章下的所有评论
        List<Review> reviews=reviewService.getListByParam("article_id",id);

        //获取发表时间最近的10条记录
        List<Review> newReviews=reviews.subList(0,10);

        //获取点赞数最多的前10条
        Collections.sort(reviews, Comparator.comparingInt(Review::getLikes));
        List<Review> hotReviews=reviews.subList(0,10);


        map.put("article",article);
        map.put("newsReviews",newReviews);
        map.put("hotReviews",hotReviews);

        return JSONObject.toJSONString(map);
    }

}

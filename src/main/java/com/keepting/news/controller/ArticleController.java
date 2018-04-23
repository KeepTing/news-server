package com.keepting.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.keepting.news.model.NewsUser;
import com.keepting.news.model.Comment;
import com.keepting.news.model.SpiderArticle;
import com.keepting.news.service.CommentService;
import com.keepting.news.service.SpiderArticleService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    CommentService commentService;

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
        List<Comment> comments=commentService.getListByParam("article_id",id);

        //获取发表时间最近的10条记录
        List<Comment> newComments=comments.subList(0,10);

        //获取点赞数最多的前10条
        Collections.sort(comments, Comparator.comparingInt(Comment::getLikes));
        List<Comment> hotComments=comments.subList(0,10);


        map.put("article",article);
        map.put("newsComments",newComments);
        map.put("hotComment",hotComments);

        return JSONObject.toJSONString(map);
    }

}

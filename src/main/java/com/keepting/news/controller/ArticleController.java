package com.keepting.news.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keepting.news.model.NewsUser;
import com.keepting.news.model.Comment;
import com.keepting.news.model.SpiderArticle;
import com.keepting.news.service.CommentService;
import com.keepting.news.service.SpiderArticleService;
import org.bson.types.ObjectId;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by keepspy on 2018/4/9.
 */
@RestController
@RequestMapping("/article")
public class ArticleController {

    int commentSize=20;
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
        int size=comments.size();

        //获取发表时间最近的20条记录
        List<Comment> newComments=comments.subList(0,size<commentSize?size:commentSize);

        map.put("article",article);
        map.put("comments",newComments);

        session.setAttribute("json",JSONObject.toJSONString(map));
        return JSONObject.toJSONString(map);
    }

//    @GetMapping("/test")
//    public void test(HttpSession session){
//        JSONObject json= JSON.parseObject((String) session.getAttribute("json"));
//        SpiderArticle article= json.getObject("article",SpiderArticle.class);
//
//        JSONArray jsonArray= json.getJSONArray("comments");
//    }

}

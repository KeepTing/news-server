package com.keepting.news.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.keepting.news.model.NewsUser;
import com.keepting.news.model.Comment;
import com.keepting.news.model.SpiderArticle;
import com.keepting.news.service.CommentService;
import com.keepting.news.service.NewsUserService;
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

    @Autowired
    NewsUserService newsUserService;

    /**
     * 根据文章id获取文章页面信息，包括文章信息，最新评论，最热评论
     * @param id
     * @param session
     * @return
     */
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable String id , HttpSession session){

        //保存当前浏览的文章id
        session.setAttribute("curArticleId",id);

        Map<String,Object> map=new HashMap<>();
        ObjectId objectId=new ObjectId(id);
        SpiderArticle article=articleService.getById(objectId);

        //根据article_id获取此篇文章下的所有评论
        List<Comment> comments=commentService.getListByParam("article_id",id);
        int size=comments.size()>20?20:comments.size();
        List<Map> newComments=new ArrayList<>();
        for(int i=0;i<size;i++){
            Comment comment=comments.get(i);
            if(comment!=null){
                Map<String,Object> commentMap=new HashMap<>();
                commentMap.put("commentId",comment.getId());
                commentMap.put("commentContent",comment.getContent());
                commentMap.put("commentTime",comment.getCreateTime());
                commentMap.put("likes",comment.getLikes());
                NewsUser user=newsUserService.getById(comment.getUser_id());
                commentMap.put("username",user.getUserName());
                commentMap.put("headImg",user.getHeadImg());

                newComments.add(commentMap);
            }

        }
//        //获取发表时间最近的20条记录
//        List<Comment> newComments=comments.subList(0,size<commentSize?size:commentSize);
//        List<Map> comments
        map.put("article",article);
        map.put("comments",newComments);


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

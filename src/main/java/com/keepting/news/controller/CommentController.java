package com.keepting.news.controller;

import com.keepting.news.model.NewsUser;
import com.keepting.news.model.Comment;
import com.keepting.news.service.NewsUserService;
import com.keepting.news.service.CommentService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by keepspy on 2018/4/23.
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;


    @Autowired
    NewsUserService newsUserService;

    /**
     * 发表评论
     * @param comment
     * @param session
     * @return
     */
    @PostMapping("/add")
    public String add(@ModelAttribute Comment comment, HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("user" );
        if(user!=null){
            comment.setLikes(0);
            comment.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
            comment.setUser_id(user.getId());
            String curArticleId= (String) session.getAttribute("curArticleId");
            comment.setArticle_id(curArticleId);

            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            comment.setCreateTime(sd.format(new Date()));

            commentService.addComment(comment);
            return "success";
        }
        return "no_login";
    }

    /**
     * 评论点赞
     * @param commentId   评论id
     * @return
     */
    @PutMapping("/like/{commentId}")
    public String like(@PathVariable("commentId") int commentId,
                       @RequestParam("type") Integer type,
                       HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("user" );
        if(user!=null){
            //更新评论，点赞数加一
            Comment comment=commentService.getById(commentId);

            if(comment!=null){
                NewsUser newsUser=newsUserService.getById(comment.getUser_id());
                if(type==0){  //取消点赞

                    //喜欢数减一
                    comment.setLikes(comment.getLikes()-1);
                    if(comment.getLikes()<0){
                        comment.setLikes(0);
                    }

                    //用户人品值减一
                    newsUser.setScore(newsUser.getScore()-1);
                    if(newsUser.getScore()<0){
                        newsUser.setScore(0);
                    }
                }
                if(type==1){  //点赞
                    //喜欢数加一
                    comment.setLikes(comment.getLikes()+1);

                    //用户人品值加一
                    newsUser.setScore(newsUser.getScore()+1);
                    if(newsUser.getScore()>100){
                        newsUser.setScore(100);
                    }
                }
                commentService.update(comment);
                newsUserService.update(newsUser);
                return  "success";
            }
            return "false";
        }
        return "no_login";
    }


    /**
     * 举报评论
     * @param comment_id  评论id
     * @param content 举报内容
     * @return
     */
    @PutMapping("/report")
    public String report(@RequestParam(value = "comment_id",required = true) int comment_id,
                        @RequestParam(value = "content",required = true) String content,HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("user" );
        if(user!=null){
            Comment comment=commentService.getById(comment_id);
            comment.setStatus(1);
            comment.setReport(content);

            commentService.update(comment);
            return "success";
        }
        return "no_login";
    }

}

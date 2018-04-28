package com.keepting.news.controller;

import com.keepting.news.model.NewsUser;
import com.keepting.news.model.Comment;
import com.keepting.news.service.NewsUserService;
import com.keepting.news.service.CommentService;
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
    @PostMapping("add")
    public String add(@ModelAttribute Comment comment, HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user" );
        if(user!=null){

            comment.setUser_id(user.getId());

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
    @GetMapping("like/{commentId}")
    public String like(@PathVariable("commentId") int commentId,HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user" );
        if(user!=null){
            //更新评论，点赞数加一
            Comment comment=commentService.getById(commentId);
            comment.setLikes(comment.getLikes()+1);
            commentService.update(comment);

            //发表评论的用户人品值加一
            user.setScore(user.getScore()+1);
            newsUserService.update(user);
            return  "success";
        }
        return "no_login";
    }


    /**
     * 举报评论
     * @param comment_id  评论id
     * @param content 举报内容
     * @return
     */
    @GetMapping("report")
    public String report(@RequestParam(value = "comment_id",required = true) int comment_id,
                        @RequestParam(value = "content",required = true) String content,HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user" );
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

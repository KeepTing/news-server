package com.keepting.news.controller;

import com.keepting.news.model.NewsUser;
import com.keepting.news.model.Review;
import com.keepting.news.service.NewsUserService;
import com.keepting.news.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.keepting.news.controller.NewsUserController.isLogin;

/**
 * Created by keepspy on 2018/4/23.
 */
@RestController
@RequestMapping("/review")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;


    @Autowired
    NewsUserService newsUserService;

    /**
     * 发表评论
     * @param review
     * @param session
     * @return
     */
    @PostMapping("add")
    public String add(@ModelAttribute Review review, HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user" );
        if(user!=null){

            review.setUser_id(user.getId());

            SimpleDateFormat sd=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            review.setCreateTime(sd.format(new Date()));

            reviewService.addReview(review);

            return "success";
        }
        return "no_login";
    }

    /**
     * 评论点赞
     * @param reviewId   评论id
     * @return
     */
    @GetMapping("like/{reviewId}")
    public String like(@PathVariable("reviewId") int reviewId,HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user" );
        if(user!=null){
            //更新评论
            Review review=reviewService.getById(reviewId);
            review.setLikes(review.getLikes()+1);
            reviewService.update(review);

            //发表评论的用户人品值加一
            user.setScore(user.getScore()+1);
            newsUserService.update(user);
            return  "success";
        }
        return "no_login";
    }


    /**
     * 举报评论
     * @param review_id  评论id
     * @param content 举报内容
     * @return
     */
    @GetMapping("report")
    public String report(@RequestParam(value = "review_id",required = true) int review_id,
                        @RequestParam(value = "content",required = true) String content,HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("cur_user" );
        if(user!=null){
            Review review=reviewService.getById(review_id);
            review.setIs_report(1);
            review.setReport(content);

            reviewService.update(review);
            return "success";
        }
        return "no_login";
    }

}

package com.keepting.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.keepting.news.model.NewsUser;
import com.keepting.news.service.NewsUserService;
import com.keepting.news.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by keepspy on 2018/4/9.
 */
@RestController
@RequestMapping("/user")
public class NewsUserController {

    String headImg="head.png";

    @Autowired
    NewsUserService newsUserService;


    /**
     * 判断是否登录
     *
     * @param session
     * @return
     */
    public static boolean isLogin(HttpSession session) {
        NewsUser user = (NewsUser) session.getAttribute("cur_user");
        return !(user == null || "".equals(user.getUserName()));
    }

    /**
     * 登陆操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String login(@ModelAttribute NewsUser user,HttpSession session){
        boolean exist=newsUserService.isExsit(user);
        if(exist==false){
            return "false";
        }

        NewsUser existUser=newsUserService.login(user);
        if(existUser!=null){  //登陆成功
            session.setAttribute("user",existUser);
            return JSONObject.toJSONString(existUser); //返回用户对象
        }
        return "false";
    }

    /**
     * 根据id获取用户
     * @param id
     * @param session
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public String getNewsUser(@PathVariable int id, HttpSession session){
        System.out.println(id);
        NewsUser user= newsUserService.getById(id);
        if(user!=null){
            session.setAttribute("cur_user",user);
            Object json= JSONObject.toJSON(user);
            return json.toString();
        }
        return "no";
    }

    /**
     * 注册操作
     * @param user
     * @return
     */
    @PostMapping(value = "/register")
    public String register(@RequestBody NewsUser user){
//        NewsUser user=new NewsUser();
//        user.setUserName((String) map.get("userName"));
//        user.setRealName((String) map.get("realName"));
//        user.setPassword((String) map.get("password"));
        user.setScore(100);
        user.setHeadImg(headImg);
        user.setCreateTime(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        if(user.getUserName()!=null && !user.getUserName().equals("")
                && user.getPassword()!=null && !user.getPassword().equals("")
                && user.getRealName()!=null && !user.getRealName().equals("")){  //基本属性检测

            boolean exsit=newsUserService.register(user);
            if(exsit==true){
                Object json= JSONObject.toJSON(user);
                return json.toString();
            }
            return "false";
        }
        return "false";
    }


//    /**
//     * 添加用户
//     * @param user
//     * @return
//     */
//    @PostMapping("/add")
//    public String addUser(@RequestBody NewsUser user){
//        if(user.getUserName() !=null && !user.getUserName().trim().equals("")
//                && user.getRealName()!=null && !user.getRealName().trim().equals("")
//                && user.getPassword()!=null && !user.getPassword().trim().equals("")){
//            int add=newsUserService.addUser(user);
//            System.out.println(add);
//            return "success";
//        }
//        return "error";
//    }


//    @GetMapping("/all")
//    public String getAll(){
//        List<NewsUser> users=newsUserService.getListByMap(new HashMap<>());
//        return JSONObject.toJSONString(users);
//    }



}

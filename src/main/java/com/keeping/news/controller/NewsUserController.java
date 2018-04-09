package com.keeping.news.controller;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.keeping.news.dao.NewsUserMapper;
import com.keeping.news.model.NewsUser;
import com.keeping.news.service.NewsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;

/**
 * Created by keepspy on 2018/4/9.
 */
@Controller
@RequestMapping("/user")
public class NewsUserController {

    @Autowired
    NewsUserService newsUserService;

    @RequestMapping("/login")
    public String login(@ModelAttribute NewsUser user){
        boolean exist=newsUserService.isExsit(user);
        if(exist==false){
            return "false";
        }

        NewsUser existUser=newsUserService.login(user);
        if(existUser!=null){  //登陆成功
            Object json= JSONObject.toJSON(existUser);
            return json.toString();
        }
        return "false";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    @ResponseBody
    public String getNewsUser(@PathVariable int id){
        System.out.println(id);
        NewsUser user= newsUserService.getById(id);
        if(user!=null){
            Object json= JSONObject.toJSON(user);
            return json.toString();
        }
        return "no";
    }

    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public String addUser(@ModelAttribute NewsUser user){
        String userName=user.getUserName();
        return "";
    }
}

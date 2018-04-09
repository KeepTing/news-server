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
@RestController
@RequestMapping("/user")
public class NewsUserController {

    @Autowired
    NewsUserService newsUserService;

    /**
     * 登陆操作
     * @param user
     * @return
     */
    @RequestMapping(value = "/login",method = RequestMethod.POST)
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
    public String getNewsUser(@PathVariable int id){
        System.out.println(id);
        NewsUser user= newsUserService.getById(id);
        if(user!=null){
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
    @RequestMapping(value = "/register",method = RequestMethod.POST)
    public String register(@ModelAttribute NewsUser user){
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
}
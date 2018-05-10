package com.keepting.news.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.keepting.news.model.Channel;
import com.keepting.news.model.NewsUser;
import com.keepting.news.model.SpiderArticle;
import com.keepting.news.model.UserChannel;
import com.keepting.news.mongo.SpringDataPageable;
import com.keepting.news.service.ChannelService;
import com.keepting.news.service.SpiderArticleService;
import com.keepting.news.service.UserChannelService;
import com.keepting.news.util.QiniuUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.BasicQuery;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by keepspy on 2018/4/10.
 */
@RestController
public class MainController {

    int pageSize=20;
    int pageCount;

    @Autowired
    SpiderArticleService articleService;

    @Autowired
    ChannelService channelService;
    @Autowired
    QiniuUtil qiniuUtil;
    /**
     * 图片上传
     * @param multipartFile
     * @param session
     * @return
     * @throws IOException
     */
    @PostMapping("/uploadImg")
    public String uploadImg(@RequestParam("file") MultipartFile multipartFile, HttpSession session) throws IOException {
        //返回的图片路径集合
        FileInputStream inputStream = (FileInputStream) multipartFile.getInputStream();
        String path = qiniuUtil.uploadImg(inputStream, "task_img_"+System.currentTimeMillis());
        List<String> imgList= (List<String>) session.getAttribute("imgList");
        if(imgList==null){
            imgList=new ArrayList<>();
        }
        imgList.add(path);
        session.setAttribute("imgList",imgList);
        return path;
    }
    /**
     * 新闻系统首页
     * @param request
     * @param session
     * @return 用户订阅的频道、未订阅的频道和推荐类文章列表json
     */
    @GetMapping("/index")
    public String index(@RequestParam(value = "channelName",required = false) String channelName, HttpServletRequest request, HttpSession session){
        NewsUser user= (NewsUser) session.getAttribute("user");
        Map<String,Object> map=new HashMap<>();
        List<Channel> channels=new ArrayList<>();  //频道列表，用户不登录为空
        List<Channel> unChannels=channelService.getAll(); //未订阅频道列表
        List<SpiderArticle> articles;  //文章列表

        if(user!=null){  //用户已登录
            channels=channelService.getChannelsByUserId(user.getId());  //订阅的频道列表
            for(Channel channel : channels){
                for(int i=0;i<unChannels.size();i++){
                    if(channel.getId()==unChannels.get(i).getId()){
                        unChannels.remove(i);
                    }
                }
            }
        }

        int startPage=0;
        if(request.getParameter("page")!=null){
            startPage = Integer.parseInt(request.getParameter("page"));
        }
        int total= 0;
        try {
            Query query=new Query();

            //如果频道名不为空，说明用户是通过点击频道链接获取文章列表
            if(channelName!=null && !channelName.equals("")){
                query.addCriteria(Criteria.where("channel").is(channelName));
            }
            total = (int) articleService.count(query);
            pageCount=total%pageSize==0?total/pageSize:total/pageSize+1;  //最大页数
            startPage=startPage>pageCount?pageCount:startPage;  //不能超过最大页
            SpringDataPageable pageable=new SpringDataPageable();
//            Query query=new Query();
            pageable.setPagenumber(startPage);
            pageable.setPagesize(pageSize);
            pageable.setSort(new Sort(Sort.Order.desc("createTime")));

            query.with(pageable);
            articles=articleService.selectList(query);

            List<JSONObject> articleJsons=new ArrayList<>();
            for(SpiderArticle article : articles){
                String img=getImg(article.getContent());
                article.setContent(clearContent(article.getContent()));
                JSONObject articleJson= (JSONObject) JSON.toJSON(article);
                articleJson.put("indexImage",img);
                articleJson.put("a_id",article.get_id().toString());
                articleJsons.add(articleJson);
            }
            map.put("channels",channels);
            map.put("unChannels",unChannels);
            map.put("articles",articleJsons);
            map.put("pageCount",10);

            return JSONObject.toJSONString(map);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }


    //从文章内容中提取图片
    public String getImg(String content){
        List<String> pics = new ArrayList<>();
        String img = "";
        Pattern p_image;
        Matcher m_image;
        //     String regEx_img = "<img.*src=(.*?)[^>]*?>"; //图片链接地址
        String regEx_img = "<img.*src\\s*=\\s*(.*?)[^>]*?>";
        p_image = Pattern.compile
                (regEx_img, Pattern.CASE_INSENSITIVE);
        m_image = p_image.matcher(content);
        while (m_image.find()) {
            // 得到<img />数据
            img = m_image.group();
            // 匹配<img>中的src数据
            Matcher m = Pattern.compile("src\\s*=\\s*\"?(.*?)(\"|>|\\s+)").matcher(img);
            while (m.find()) {
                pics.add(m.group(1));
            }
        }
        if(pics.size()>0)
        return pics.get(0);
        return "";
    }


    //去掉内容中的所有标签
    public String clearContent(String content){
        content=content.replaceAll("]*>","");
        content=content.replaceAll(" ]*/>","");
        return content;
    }
}

# news-server
## 基于springboot的新闻聚合与订阅系统
### :beetle: 项目简介： 
项目主要分为两大模块（新闻系统、爬虫系统），新闻系统主要实现对文章数据进行抓取并分类聚合，用户可以对不同频道进行订阅并浏览相应频道的文章；以下对这两个系统的详细设计及功能说明；

### :beetle: 框架支持： 

  - Spring
  - SpringBoot
  - Mybatis
  - WebMagic(轻量级爬虫框架)
  - PhantomJS（基于webkit的JavaScript API）

### :beetle: 数据库设计：

|**DB**|**VERSION**|**LOCATION:PORT**|
|:---|:---|:---|
|Mysql|14.14 Distrib 5.6.39|123.206.14.239:3306|
|MongoDB|win32-x86_64-2008plus-ssl-3.4.4|127.0.0.1:27017|

### :beetle: 主要功能： 
 
 #### :bell: 爬虫系统：

   主要实现文章站点的抓取功能，包括垂直类站点（比如某些专注于军事类、时政类、科技类等文章的站点），以及水平类站点（比如头条、新浪、搜狐、凤凰网等综合站点）； 爬虫系统这里只给出功能及设计说明，源码托管在本人gitlab私服。

- 主要实现：

:pushpin: 对于简单文章站点,直接配置文章属性（标题、来源（作者）、时间、内容等）的规则（CSS或Xpath）;

:pushpin: 对于动态网页，首先使用PaantomJS进行动态渲染，获取渲染后的页面源码，然后进行解析；

 #### :bell: 新闻系统

  主要实现用户对一些频道（军事、历史、时政等）进行订阅及对自己订阅的频道下的文章进行浏览的功能，

### :beetle: 详细设计： 

#### :pushpin: 系统相关配置:

- 主要是抓取站点及计划任务相关配置，站点配置包括首页解析方式、底页提取规则、底页解析规则、抓取周期等相关配置；计划任务是根据抓取周期进行配置；

- 爬虫系统配置图

![](https://github.com/KeepTing/news-server/blob/master/src/main/images/爬虫系统配置图.png)


#### :pushpin: 系统结构图:

![](https://github.com/KeepTing/news-server/blob/master/src/main/images/爬虫系统结构图.png)

#### :pushpin: 系统模块图:

![](https://github.com/KeepTing/news-server/blob/master/src/main/images/爬虫系统模块图.png)
    

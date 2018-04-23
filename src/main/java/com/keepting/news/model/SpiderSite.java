package com.keepting.news.model;

import java.io.Serializable;

/**
 * Created by mayz on 2016/11/16.
 */
public class SpiderSite implements Serializable,Cloneable  {
    private int id;
//    媒体链接
    private String linkUrl="";
//    自媒体名
    private String mediaName="";

//    首页解析方式
    private String site_type="";
//    首页提取规则
    private String hub_role="";

//    文章解析类型xpath/css
    private String role_type="";
//    文章内容规则
    private String detailContent="";
//    文章日期规则
    private String detailDate="";
//    文章标题规则
    private String detailTitle="";
//    文章来源规则
    private String detailFrom="";
//    文章摘要规则
    private String detailAbstract="";
//    可抓取链接总数
    private int total_link=0;
//    抓取数
    private int crawl_link=0;
//    抓取结果
    private int crawlResult=0;
    /**
     * [['','否'],[1,'仅列表页需要'],[2,'列表、所有文章页都需要'],[3,'列表、首页都需要'],[4,'所有文章页需要'],[5,'首页需要']]
     */
    private int phantomjs=0;

//    频道
    private String channel="";
//    抓取周期
    private int cycle=15;
//    创建时间
    private String createTime="";

    /**
     * 列表页区块规则（css）
     */
    private String listAreaRule="";
    /**抓到的地址需要替换的地方http://www.zsheying.com/xueyuan/fg/*/
    private String urlReplace="";
//    状态
    private int status;


    public String getDetailContent() {
        return detailContent;
    }

    public void setDetailContent(String detailContent) {
        this.detailContent = detailContent;
    }

    public String getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    public String getDetailTitle() {
        return detailTitle;
    }

    public void setDetailTitle(String detailTitle) {
        this.detailTitle = detailTitle;
    }

    public String getDetailFrom() {
        return detailFrom;
    }

    public void setDetailFrom(String detailFrom) {
        this.detailFrom = detailFrom;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getMediaName() {
        return mediaName;
    }

    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    public String getSite_type() {
        return site_type;
    }

    public void setSite_type(String site_type) {
        this.site_type = site_type;
    }

    public String getHub_role() {
        return hub_role;
    }

    public void setHub_role(String hub_role) {
        this.hub_role = hub_role;
    }


    public String getRole_type() {
        return role_type;
    }

    public void setRole_type(String role_type) {
        this.role_type = role_type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getTotal_link() {
        return total_link;
    }

    public void setTotal_link(int total_link) {
        this.total_link = total_link;
    }

    public int getCrawl_link() {
        return crawl_link;
    }

    public void setCrawl_link(int crawl_link) {
        this.crawl_link = crawl_link;
    }

    public int getCrawlResult() {
        return crawlResult;
    }

    public void setCrawlResult(int crawlResult) {
        this.crawlResult = crawlResult;
    }

    public String getDetailAbstract() {
        return detailAbstract;
    }

    public void setDetailAbstract(String detailAbstract) {
        this.detailAbstract = detailAbstract;
    }


    public int getPhantomjs() {
        return phantomjs;
    }

    public void setPhantomjs(int phantomjs) {
        this.phantomjs = phantomjs;
    }


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getListAreaRule() {
        return listAreaRule;
    }

    public void setListAreaRule(String listAreaRule) {
        this.listAreaRule = listAreaRule;
    }


    public String getUrlReplace() {
        return urlReplace;
    }

    public void setUrlReplace(String urlReplace) {
        this.urlReplace = urlReplace;
    }


    public int getCycle() {
        return cycle;
    }

    public void setCycle(int cycle) {
        this.cycle = cycle;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getChannel() {
        return channel;
    }

    public SpiderSite clone() throws CloneNotSupportedException {
        return (SpiderSite) super.clone();
    }
}

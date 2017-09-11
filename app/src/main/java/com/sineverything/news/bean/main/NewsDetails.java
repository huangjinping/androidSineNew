package com.sineverything.news.bean.main;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class NewsDetails extends BaseBean {


    /**
     * hits : 12
     * classId : 226
     * createTime : 1490036679000
     * linkUrl : http://singaporetong.com/cms/detail.shtml?id=1817
     * id : 1817
     * synopsis : 新加坡F1好大阵势，10大宗教领袖一起为它保驾护航！
     * source : 新加坡万事通
     * title : 新加坡F1好大阵势，10大宗教领袖一起为它保驾护航！
     * content :
     */

    private String hits;
    private String classId;
    private String createTime;
    private String linkUrl;
    private String id;
    private String synopsis;
    private String source;
    private String title;
    private String content;

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

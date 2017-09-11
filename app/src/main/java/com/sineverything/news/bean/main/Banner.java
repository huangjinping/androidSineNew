package com.sineverything.news.bean.main;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class Banner extends BaseBean {

    /**
     * id : 4480
     * remarks : null
     * createDate : null
     * updateDate : null
     * title : “足球·友谊”国际少儿足球论坛在圣彼得堡隆重举行
     * cover : http://47.88.169.219:8081//uploads/2017/07//Screen-Shot-2017-07-08-at-3.18.47-PM.png
     * createTime : 2017-07-08 15:28:38.0
     * linkUrl : http://singaporetong.com/cms/detail.shtml?id=4480
     * source : 新加坡万事通
     * hits : 9
     */

    private String id;
    private String remarks;
    private String createDate;
    private String updateDate;
    private String title;
    private String cover;
    private String createTime;
    private String linkUrl;
    private String source;
    private String hits;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
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

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getHits() {
        return hits;
    }

    public void setHits(String hits) {
        this.hits = hits;
    }
}

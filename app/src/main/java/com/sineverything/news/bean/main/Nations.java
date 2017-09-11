package com.sineverything.news.bean.main;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/9.
 * email : huangjinping@hdfex.com
 */

public class Nations extends BaseBean {


    /**
     * id : 23
     * remarks : null
     * createDate : null
     * updateDate : null
     * nationEn : Bermuda
     * nationCn : 百慕大群岛
     * nationShort : BM
     * nationCode : 1441
     */

    private String id;
    private String remarks;
    private String createDate;
    private String updateDate;
    private String nationEn;
    private String nationCn;
    private String nationShort;
    private String nationCode;

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

    public String getNationEn() {
        return nationEn;
    }

    public void setNationEn(String nationEn) {
        this.nationEn = nationEn;
    }

    public String getNationCn() {
        return nationCn;
    }

    public void setNationCn(String nationCn) {
        this.nationCn = nationCn;
    }

    public String getNationShort() {
        return nationShort;
    }

    public void setNationShort(String nationShort) {
        this.nationShort = nationShort;
    }

    public String getNationCode() {
        return nationCode;
    }

    public void setNationCode(String nationCode) {
        this.nationCode = nationCode;
    }

    @Override
    public String toString() {
        return "Nations{" +
                "id='" + id + '\'' +
                ", remarks='" + remarks + '\'' +
                ", createDate='" + createDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", nationEn='" + nationEn + '\'' +
                ", nationCn='" + nationCn + '\'' +
                ", nationShort='" + nationShort + '\'' +
                ", nationCode='" + nationCode + '\'' +
                '}';
    }
}

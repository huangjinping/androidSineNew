package com.sineverything.news.bean.order;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class GoodsInfo extends BaseBean {

    /**
     * id : null
     * remarks : null
     * createDate : null
     * updateDate : null
     * goodsId : null
     * goodsType : null
     * goodsCount : null
     * goodsPrice : null
     * goodsAllPrice : null
     * goodsMainphotoPath : http://47.88.169.219:8081/null
     */

    private String id;
    private String remarks;
    private String createDate;
    private String updateDate;
    private String goodsId;
    private String goodsType;
    private String goodsCount;
    private String goodsPrice;
    private String goodsAllPrice;
    private String goodsMainphotoPath;
    private String goodsName;

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsType() {
        return goodsType;
    }

    public void setGoodsType(String goodsType) {
        this.goodsType = goodsType;
    }

    public String getGoodsCount() {
        return goodsCount;
    }

    public void setGoodsCount(String goodsCount) {
        this.goodsCount = goodsCount;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsAllPrice() {
        return goodsAllPrice;
    }

    public void setGoodsAllPrice(String goodsAllPrice) {
        this.goodsAllPrice = goodsAllPrice;
    }

    public String getGoodsMainphotoPath() {
        return goodsMainphotoPath;
    }

    public void setGoodsMainphotoPath(String goodsMainphotoPath) {
        this.goodsMainphotoPath = goodsMainphotoPath;
    }
}

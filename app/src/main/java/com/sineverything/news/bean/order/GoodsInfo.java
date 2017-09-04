package com.sineverything.news.bean.order;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class GoodsInfo extends BaseBean {

    private String goodsId;
    private String goodsType;
    private String goodsCount;
    private String goodsPrice;
    private String goodsAllPrice;
    private String goodsMainphotoPath;

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

package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/5.
 * email : huangjinping@hdfex.com
 */

public class GoodsInfos extends BaseBean {

    private String goodsId;
    private String goodsCount;
    private String goodsPrice;
    private String goodsAllPrice;
    private String goodsGspIds;
    private String goodsGspVal;

    public String getGoodsGspIds() {
        return goodsGspIds;
    }

    public void setGoodsGspIds(String goodsGspIds) {
        this.goodsGspIds = goodsGspIds;
    }

    public String getGoodsGspVal() {
        return goodsGspVal;
    }

    public void setGoodsGspVal(String goodsGspVal) {
        this.goodsGspVal = goodsGspVal;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
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
}

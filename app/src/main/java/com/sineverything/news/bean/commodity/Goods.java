package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class Goods extends BaseBean {

    private String goodsId;
    private String goodsName;
    private String goodsMainPhoto;
    private String goodsPrice;
    private String goodsSaleNum;
    private String goodsClick;
    private String goodsCollect;
    private String wellEvaluate;
    private String conunt;

    public String getConunt() {
        return conunt;
    }

    public void setConunt(String conunt) {
        this.conunt = conunt;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsMainPhoto() {
        return goodsMainPhoto;
    }

    public void setGoodsMainPhoto(String goodsMainPhoto) {
        this.goodsMainPhoto = goodsMainPhoto;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getGoodsSaleNum() {
        return goodsSaleNum;
    }

    public void setGoodsSaleNum(String goodsSaleNum) {
        this.goodsSaleNum = goodsSaleNum;
    }

    public String getGoodsClick() {
        return goodsClick;
    }

    public void setGoodsClick(String goodsClick) {
        this.goodsClick = goodsClick;
    }

    public String getGoodsCollect() {
        return goodsCollect;
    }

    public void setGoodsCollect(String goodsCollect) {
        this.goodsCollect = goodsCollect;
    }

    public String getWellEvaluate() {
        return wellEvaluate;
    }

    public void setWellEvaluate(String wellEvaluate) {
        this.wellEvaluate = wellEvaluate;
    }
}

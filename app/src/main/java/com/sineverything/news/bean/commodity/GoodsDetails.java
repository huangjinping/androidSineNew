package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.BaseBean.BaseBean;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class GoodsDetails extends BaseBean {


    /**
     * goodsSalenum : 0
     * badEvaluate : null
     * goodsId : 281
     * goodsService :
     * goodsVolume : null
     * goodsMainPhoto : http://47.88.169.219:8081/upload/system/self_goods/2017/08/187f876264-83b8-4383-acdb-8ec81739ec56.jpg
     * goodsPhotos : ["http://47.88.169.219:8081/upload/system/self_goods/2017/08/187f876264-83b8-4383-acdb-8ec81739ec56.jpg"]
     * goodsInventory : 9967
     * goodsWeight : null
     * goodsCod : -1
     * wellEvaluate : null
     * middleEvaluate : null
     * classId : 848
     * goodsPrice : 0.10
     * storePrice : 0.10
     * goodsClick : 59
     * goodsCollect : 1
     * goodsDetailsMobile :
     * goodsSerial : 201708180918
     * goodsName : 热门配套01
     * goodsFee : null
     */

    private String goodsSalenum;
    private String badEvaluate;
    private String goodsId;
    private String goodsService;
    private String goodsVolume;
    private String goodsMainPhoto;
    private String goodsInventory;
    private String goodsWeight;
    private String goodsCod;
    private String wellEvaluate;
    private String middleEvaluate;
    private String classId;
    private String goodsPrice;
    private String storePrice;
    private String goodsClick;
    private String goodsCollect;
    private String goodsDetailsMobile;
    private String goodsSerial;
    private String goodsName;
    private String goodsFee;
    private String gspIds;

    private List<String> goodsPhotos;
    private List<SpecsParent> specsList;

    public List<SpecsParent> getSpecsList() {
        return specsList;
    }

    public void setSpecsList(List<SpecsParent> specsList) {
        this.specsList = specsList;
    }

    public String getGoodsSalenum() {
        return goodsSalenum;
    }

    public void setGoodsSalenum(String goodsSalenum) {
        this.goodsSalenum = goodsSalenum;
    }

    public String getBadEvaluate() {
        return badEvaluate;
    }

    public void setBadEvaluate(String badEvaluate) {
        this.badEvaluate = badEvaluate;
    }

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsService() {
        return goodsService;
    }

    public void setGoodsService(String goodsService) {
        this.goodsService = goodsService;
    }

    public String getGoodsVolume() {
        return goodsVolume;
    }

    public void setGoodsVolume(String goodsVolume) {
        this.goodsVolume = goodsVolume;
    }

    public String getGoodsMainPhoto() {
        return goodsMainPhoto;
    }

    public void setGoodsMainPhoto(String goodsMainPhoto) {
        this.goodsMainPhoto = goodsMainPhoto;
    }

    public String getGoodsInventory() {
        return goodsInventory;
    }

    public void setGoodsInventory(String goodsInventory) {
        this.goodsInventory = goodsInventory;
    }

    public String getGoodsWeight() {
        return goodsWeight;
    }

    public void setGoodsWeight(String goodsWeight) {
        this.goodsWeight = goodsWeight;
    }

    public String getGoodsCod() {
        return goodsCod;
    }

    public void setGoodsCod(String goodsCod) {
        this.goodsCod = goodsCod;
    }

    public String getWellEvaluate() {
        return wellEvaluate;
    }

    public void setWellEvaluate(String wellEvaluate) {
        this.wellEvaluate = wellEvaluate;
    }

    public String getMiddleEvaluate() {
        return middleEvaluate;
    }

    public void setMiddleEvaluate(String middleEvaluate) {
        this.middleEvaluate = middleEvaluate;
    }

    public String getClassId() {
        return classId;
    }

    public void setClassId(String classId) {
        this.classId = classId;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }

    public String getStorePrice() {
        return storePrice;
    }

    public void setStorePrice(String storePrice) {
        this.storePrice = storePrice;
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

    public String getGoodsDetailsMobile() {
        return goodsDetailsMobile;
    }

    public void setGoodsDetailsMobile(String goodsDetailsMobile) {
        this.goodsDetailsMobile = goodsDetailsMobile;
    }

    public String getGoodsSerial() {
        return goodsSerial;
    }

    public void setGoodsSerial(String goodsSerial) {
        this.goodsSerial = goodsSerial;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsFee() {
        return goodsFee;
    }

    public void setGoodsFee(String goodsFee) {
        this.goodsFee = goodsFee;
    }

    public List<String> getGoodsPhotos() {
        return goodsPhotos;
    }

    public void setGoodsPhotos(List<String> goodsPhotos) {
        this.goodsPhotos = goodsPhotos;
    }

    public String getGspIds() {
        return gspIds;
    }

    public void setGspIds(String gspIds) {
        this.gspIds = gspIds;
    }
}

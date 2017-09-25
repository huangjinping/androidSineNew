package com.sineverything.news.bean.my;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/19.
 * email : huangjinping@hdfex.com
 */

public class Goodscart  extends BaseBean{


    /**
     * id : null
     * remarks : null
     * createDate : null
     * updateDate : null
     * cartId : 204
     * goodsId : 347
     * goodsName : 规格测试
     * count : 1
     * price : 10.00
     * specInfo : 出行人群：家庭套餐<br>日期：9.1<br>
     * mainPhoto : upload/system/self_goods/2017/09/08/0beff71b-4d14-4f21-9440-b90c4c9d4f8c.png
     */

    private String id;
    private String remarks;
    private String createDate;
    private String updateDate;
    private String cartId;
    private String goodsId;
    private String goodsName;
    private int count;
    private String price;
    private String specInfo;
    private String mainPhoto;

    private boolean selected;

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
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

    public String getCartId() {
        return cartId;
    }

    public void setCartId(String cartId) {
        this.cartId = cartId;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSpecInfo() {
        return specInfo;
    }

    public void setSpecInfo(String specInfo) {
        this.specInfo = specInfo;
    }

    public String getMainPhoto() {
        return mainPhoto;
    }

    public void setMainPhoto(String mainPhoto) {
        this.mainPhoto = mainPhoto;
    }
}

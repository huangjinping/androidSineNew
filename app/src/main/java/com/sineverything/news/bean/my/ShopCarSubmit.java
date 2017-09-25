package com.sineverything.news.bean.my;

import com.sineverything.news.bean.BaseBean.BaseBean;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/23.
 * email : huangjinping@hdfex.com
 */

public class ShopCarSubmit extends BaseBean {

    private String selectedIds;
    private String totalPrice;
    private List<Goodscart>  goodscarts;

    public String getSelectedIds() {
        return selectedIds;
    }

    public void setSelectedIds(String selectedIds) {
        this.selectedIds = selectedIds;
    }

    public String getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(String totalPrice) {
        this.totalPrice = totalPrice;
    }

    public List<Goodscart> getGoodscarts() {
        return goodscarts;
    }

    public void setGoodscarts(List<Goodscart> goodscarts) {
        this.goodscarts = goodscarts;
    }
}

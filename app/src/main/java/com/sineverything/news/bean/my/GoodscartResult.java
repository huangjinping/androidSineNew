package com.sineverything.news.bean.my;

import com.sineverything.news.bean.BaseBean.BaseBean;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/19.
 * email : huangjinping@hdfex.com
 */

public class GoodscartResult extends BaseBean {

    private String totalAmount;
    private List<Goodscart> cartList;

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public List<Goodscart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Goodscart> cartList) {
        this.cartList = cartList;
    }
}

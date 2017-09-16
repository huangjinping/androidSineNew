package com.sineverything.news.bean.order;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/12.
 * email : huangjinping@hdfex.com
 */

public class SubmitOrder extends BaseBean {

    private String  orderId;

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

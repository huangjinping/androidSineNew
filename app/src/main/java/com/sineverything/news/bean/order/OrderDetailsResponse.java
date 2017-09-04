package com.sineverything.news.bean.order;

import com.sineverything.news.bean.Response;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class OrderDetailsResponse extends Response {
    private OrderDetails result;

    public OrderDetails getResult() {
        return result;
    }

    public void setResult(OrderDetails result) {
        this.result = result;
    }
}

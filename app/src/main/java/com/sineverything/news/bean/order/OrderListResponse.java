package com.sineverything.news.bean.order;

import com.sineverything.news.bean.Response;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class OrderListResponse extends Response {



    private List<Order>  result;

    public List<Order> getResult() {
        return result;
    }

    public void setResult(List<Order> result) {
        this.result = result;
    }
}

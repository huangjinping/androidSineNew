package com.sineverything.news.bean.order;

import com.sineverything.news.bean.Response;

/**
 * author Created by harrishuang on 2017/9/12.
 * email : huangjinping@hdfex.com
 */

public class SubmitOrderResponse extends Response {

    private SubmitOrder result;

    public SubmitOrder getResult() {
        return result;
    }

    public void setResult(SubmitOrder result) {
        this.result = result;
    }
}

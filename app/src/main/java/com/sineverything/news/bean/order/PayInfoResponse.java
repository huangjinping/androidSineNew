package com.sineverything.news.bean.order;

import com.sineverything.news.bean.Response;

/**
 * author Created by harrishuang on 2017/9/10.
 * email : huangjinping@hdfex.com
 */

public class PayInfoResponse  extends Response{

    private PayInfo result;

    public PayInfo getResult() {
        return result;
    }

    public void setResult(PayInfo result) {
        this.result = result;
    }
}

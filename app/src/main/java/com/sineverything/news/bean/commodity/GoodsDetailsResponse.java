package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.Response;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class GoodsDetailsResponse extends Response {


    private GoodsDetails   result;

    public GoodsDetails getResult() {
        return result;
    }

    public void setResult(GoodsDetails result) {
        this.result = result;
    }
}

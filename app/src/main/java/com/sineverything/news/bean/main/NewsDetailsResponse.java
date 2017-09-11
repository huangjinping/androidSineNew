package com.sineverything.news.bean.main;

import com.sineverything.news.bean.BaseBean.BaseBean;
import com.sineverything.news.bean.Response;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class NewsDetailsResponse extends Response {


    private NewsDetails result;

    public NewsDetails getResult() {
        return result;
    }

    public void setResult(NewsDetails result) {
        this.result = result;
    }
}

package com.sineverything.news.bean.main;

import com.sineverything.news.bean.Response;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/5.
 * email : huangjinping@hdfex.com
 */

public class NewsItemResponse extends Response {

    private List<NewsItem> result;

    public List<NewsItem> getResult() {
        return result;
    }

    public void setResult(List<NewsItem> result) {
        this.result = result;
    }
}

package com.sineverything.news.bean.main;

import com.sineverything.news.bean.Response;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class BannerResponse extends Response {

    private List<Banner>  result;

    public List<Banner> getResult() {
        return result;
    }

    public void setResult(List<Banner> result) {
        this.result = result;
    }
}

package com.sineverything.news.bean.main;

import com.sineverything.news.bean.Response;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/9.
 * email : huangjinping@hdfex.com
 */

public class NationsResponse extends Response {

    private List<Nations> result;

    public List<Nations> getResult() {
        return result;
    }

    public void setResult(List<Nations> result) {
        this.result = result;
    }
}

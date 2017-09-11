package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.Response;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class MenuResponse extends Response {


    private List<MenuItem>  result;

    public List<MenuItem> getResult() {
        return result;
    }

    public void setResult(List<MenuItem> result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "MenuResponse{" +
                "result=" + result +
                '}';
    }
}

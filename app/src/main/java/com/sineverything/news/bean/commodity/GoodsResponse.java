package com.sineverything.news.bean.commodity;

import com.sineverything.news.bean.Response;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/3.
 * email : huangjinping@hdfex.com
 */

public class GoodsResponse extends Response {


    private List<Goods>  result;

    public List<Goods> getResult() {
        return result;
    }

    public void setResult(List<Goods> result) {
        this.result = result;
    }
}

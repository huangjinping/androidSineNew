package com.sineverything.news.bean.main;

import com.sineverything.news.bean.Response;

import java.util.List;

/**
 * author Created by harrishuang on 2017/9/24.
 * email : huangjinping@hdfex.com
 */

public class CommentsResponse extends Response {

    private List<Comments> result;

    public List<Comments> getResult() {
        return result;
    }

    public void setResult(List<Comments> result) {
        this.result = result;
    }
}

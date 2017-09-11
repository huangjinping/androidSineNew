package com.sineverything.news.bean.main;

import com.sineverything.news.bean.Response;

/**
 * author Created by harrishuang on 2017/9/5.
 * email : huangjinping@hdfex.com
 */

public class RegisterResponse extends Response {
    private User result;

    public User getResult() {
        return result;
    }

    public void setResult(User result) {
        this.result = result;
    }
}

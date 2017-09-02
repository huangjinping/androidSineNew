package com.sineverything.news.bean.service;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/8/30.
 * email : huangjinping@hdfex.com
 */

public class ChildMenu extends BaseBean {

    private String title;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public ChildMenu() {
    }

    public ChildMenu(String title) {
        this.title = title;
    }
}

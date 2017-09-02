package com.sineverything.news.bean.service;

import com.sineverything.news.bean.BaseBean.BaseBean;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/30.
 * email : huangjinping@hdfex.com
 */

public class ServiceMenu  extends BaseBean{

    private int icon;
    private String title;
    private int backgroundColor;
    private List<ChildMenu> list;

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getBackgroundColor() {
        return backgroundColor;
    }

    public void setBackgroundColor(int backgroundColor) {
        this.backgroundColor = backgroundColor;
    }

    public List<ChildMenu> getList() {
        return list;
    }

    public void setList(List<ChildMenu> list) {
        this.list = list;
    }
}

package com.sineverything.news.bean.main;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/5.
 * email : huangjinping@hdfex.com
 */

public class User extends BaseBean {


    /**
     * nickName : 136******17@phone
     * id : 90
     * userName : 13611290917@phone
     * token : 1iepyfdbd3f3q
     */

    private String nickName;
    private String id;
    private String userName;
    private String token;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

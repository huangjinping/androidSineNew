package com.sineverything.news.bean.main;

import com.sineverything.news.bean.BaseBean.BaseBean;

/**
 * author Created by harrishuang on 2017/9/5.
 * email : huangjinping@hdfex.com
 */

public class User extends BaseBean {


    /**
     * nickname : 136******17@phone
     * id : 96
     * token : 1jezbudj89o4l
     */

    private String nickname;
    private String id;
    private String token;
    private String loginName;

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}

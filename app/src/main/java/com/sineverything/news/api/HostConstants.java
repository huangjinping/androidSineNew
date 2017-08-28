package com.sineverything.news.api;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public interface HostConstants {
    /**
     * 下载录播图
     */
    String BANNER = "http://singaporetong.com/wp-json/wp/v2/posts/?categories=1";


    /**
     * 列表
     */
    String NEWSLIST = "http://tong.mayslife.com/wp-json/wp/v2/posts/?categories=1";

//    String  NEWSLIST="http://singaporetong.com/wp-json/wp/v2/posts/?categories=226";
    /**
     * 详情
     */

//    String NEWDETAILS="http://tong.mayslife.com/wp-json/wp/v2/posts/";
    String NEWDETAILS = "http://tong.mayslife.com/wp-json/wp/v2/posts/";

    /**
     * 搜索
     */
    String SEARCH = "http://tong.mayslife.com/wp-json/wp/v2/posts?search=";
    /**
     * 访问
     */
    String COMMENTS = "http://tong.mayslife.com/wp-json/wp/v2/comments/?post=";


    String HOME_HOST = "http://47.94.58.196:8080/hd-merchant-app/mobile";
    /**
     *收取收取信息
     */
    String SEND_SMS_CODE = HOME_HOST + "/login/sendSmsCode";


}

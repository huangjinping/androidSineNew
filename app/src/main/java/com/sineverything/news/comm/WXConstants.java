package com.sineverything.news.comm;

/**
 * author Created by harrishuang on 2017/8/17.
 * email : huangjinping@hdfex.com
 */

public interface WXConstants {
    public static final String APP_ID = "wxff9ff5895fedf32d";
    //	wxff9ff5895fedf32d
    //用code换access_token
    public static String GET_REQUEST_ACCESS_TOKEN =
            "https://api.weixin.qq.com/sns/oauth2/access_token?appid=";
    //获取微信用户资料
    public static String GET_REQUEST_USER_INFO =
            "https://api.weixin.qq.com/sns/userinfo?access_token=";
    //微信支付商户号
    public static final String WECHAT_MCH_ID = "1458293902";
    //API密钥:AppSecret是APPID对应的接口密码，用于获取接口调用凭证access_token时使用
    public static final String WECHAT_API_KEY = "0c666651646f6fb57009cf938452c130";
    public static class ShowMsgActivity {
        public static final String STitle = "showmsg_title";
        public static final String SMessage = "showmsg_message";
        public static final String BAThumbData = "showmsg_thumb_data";
    }
}

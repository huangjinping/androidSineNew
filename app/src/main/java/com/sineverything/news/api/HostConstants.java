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


//    String  NEWSLIST="http://singaporetong.com/wp-json/wp/v2/posts/?categories=226";
    /**
     * 详情
     */

//    String NEWDETAILS="http://tong.mayslife.com/wp-json/wp/v2/posts/";




    String HOME_HOST = "http://47.94.58.196:8080/hd-merchant-app/mobile";
    /**
     * 收取收取信息
     */
    String SEND_SMS_CODE = HOME_HOST + "/login/sendSmsCode";
    /**
     * 获取城市
     */
    String GET_NATIONS = HOME_HOST + "/message/getNations";
    /**
     * 注册信息
     */
    String REGISTER = HOME_HOST + "/register/registerByPhone";

    /**
     * 登录界面
     */
    String LOGIN = HOME_HOST + "/login/loginByPhone";
    /**
     * 热门分类
     */
    String INDEX_HOTS = HOME_HOST + "/index/hots";
    /**
     * 一级菜单
     */
    String LEFT_CATEGOGORIES = HOME_HOST + "/goods/left-categories";


    String CATEGORIES_BY_PID = HOME_HOST + "/goods/categories-by-pid";
    /**
     * 获取首页banner
     */
    String GET_INDEX_BANNDER = HOME_HOST + "/cms/getIndexBanner";


    String RECOMMDEND_GOODS = HOME_HOST + "/index/recommendGoods";
    /**
     * 商品列表
     */
    String GOODS_LIST = HOME_HOST + "/goods/list";

    /**
     * 1.05新闻详情
     */
    String CMS_DETAIL = HOME_HOST + "/cms/detail";
    /**
     * 相关热点新闻
     */
    String RELATE_HOST = HOME_HOST + "/cms/relateHots";
    /**
     * 问题
     */
    String VERSION_CHECK = HOME_HOST + "/version/check";
    /**
     * 列表
     */
    String SERVICE_INFO_LIST = HOME_HOST + "/cms/getServiceInfoList";

    String GOODS_DETAILS = HOME_HOST + "/goods/detail";
    /**
     * 提交订单
     */
    String ORDER_SUBMIT = HOME_HOST + "/order/submit";

    String PAYMENT_ALIPAY = HOME_HOST + "/payment/alipay";
    /**
     * 首页页面
     */
    String RELATE_HOTS = HOME_HOST + "/cms/relateHots";

    /**
     * 列表
     */
    String NEWSLIST = HOME_HOST + "/cms/getIndexCmsList";

    String NEWDETAILS = HOME_HOST + "/cms/detail";
    /**
     * 搜索
     */
    String SEARCH = HOME_HOST + "/cms/search";
    /**
     * 订单列表
     */
    String ORDER_LIST = HOME_HOST + "/order/list";
    /**
     *
     */
    String ORDER_DETAIL = HOME_HOST + "/order/detail";


    String UPDATE_URL = HOME_HOST + "/version/check";

    /**
     * 加入购物车
     */
    String ADD_GOODSCART = HOME_HOST + "/goodscart/addGoodsCart";
    /**
     * 6.1获取规格对应的价格和库存
     */
    String GET_GOODS_GSP = HOME_HOST + "/goods/getGoodsGsp";
    /**
     * 购物车列表
     */
    String GOODS_CART_LIST = HOME_HOST + "/goodscart/list";
    /**
     * 购物车删除
     */
    String GOODS_CART_DELETE = HOME_HOST + "/goodscart/delete";
    /**
     * 购物车编辑
     */
    String GOODS_CART_EDIT = HOME_HOST + "/goodscart/edit";
    /**
     * 购物车下单接口
     */
    String ORDER_SUBMIT_CART = HOME_HOST + "/order/submitCart";
    /**
     * 访问
     */
    String COMMENTS = HOME_HOST+"/cms/comments/list";
    /**
     * 访问
     */
    String  COMMENTS_ADD =HOME_HOST+"/cms/comments/add";

}

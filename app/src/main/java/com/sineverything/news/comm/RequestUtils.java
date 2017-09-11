package com.sineverything.news.comm;

import android.content.Context;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.commodity.MenuResponse;

import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/9/4.
 * email : huangjinping@hdfex.com
 */

public class RequestUtils {


    /**
     * 一级菜单
     */
    public static void loadDetails(Context context, String goodsId) {
        final BaseActivity activity = (BaseActivity) context;

        activity.startProgressDialog();
        OkHttpUtils.post()
                .url(HostConstants.GOODS_DETAILS)
                .addParams("goodsId", goodsId)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                activity.stopProgressDialog();

            }

            @Override
            public void onResponse(String response) {
                MenuResponse menuResponse = GsonUtil.changeGsonToBean(response, MenuResponse.class);


                if (menuResponse != null) {
                    if (activity.isOkCode(menuResponse.getCode(), menuResponse.getMessage())) {
                    }
                }
            }
        });
    }
}

package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/5/21.
 * email : huangjinping@hdfex.com
 */

public class PhoneLoginActivity extends BaseActivity {

    @Bind(R.id.bt_verification_code)
    Button btVerificationCode;

    @Override
    public int getLayoutId() {
        return R.layout.act_phonelogin;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, PhoneLoginActivity.class);
        context.startActivity(intent);
    }

    /**
     *
     */
    @OnClick(R.id.bt_verification_code)
    public void onSendMessageAll() {
        startProgressDialog();
        OkHttpUtils.post().url(HostConstants.SEND_SMS_CODE)
                .addParams("type", "register")
                .addParams("mobile", "13611290917")
                .addParams("nationCode", "+86")
                .addParams("smsSn", System.currentTimeMillis() + "")
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
                stopProgressDialog();

            }

            @Override
            public void onResponse(String response) {
                Log.d("okhttp", response);
            }
        });
    }


}

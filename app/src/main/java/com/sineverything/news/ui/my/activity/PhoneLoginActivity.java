package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.bean.SHARE_MEDIA;

import java.util.Map;

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
        PlatformConfig.setWeixin("123123", "123412341234");
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, PhoneLoginActivity.class);
        context.startActivity(intent);
    }



    private UMAuthListener umAuthListener = new UMAuthListener() {
        @Override
        public void onStart(SHARE_MEDIA platform) {
            //授权开始的回调
        }

        @Override
        public void onComplete(SHARE_MEDIA platform, int action, Map<String, String> data) {
            Toast.makeText(getApplicationContext(), "Authorize succeed", Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onError(SHARE_MEDIA platform, int action, Throwable t) {
            Toast.makeText(getApplicationContext(), "Authorize fail", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onCancel(SHARE_MEDIA platform, int action) {
            Toast.makeText(getApplicationContext(), "Authorize cancel", Toast.LENGTH_SHORT).show();
        }
    };

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode, resultCode, data);

    }

    /**
     *
     */
    @OnClick(R.id.bt_verification_code)
    public void onSendMessageAll() {

//        UMShareAPI.get(mContext).doOauthVerify(this, SHARE_MEDIA.WEIXIN.toSnsPlatform().mPlatform, umAuthListener);

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

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }


}

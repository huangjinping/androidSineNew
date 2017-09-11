package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.text.Html;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.baserx.RxBus;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.main.Nations;
import com.sineverything.news.bean.main.RegisterResponse;
import com.sineverything.news.bean.main.SmsCode;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;
import rx.Subscriber;

/**
 * author Created by harrishuang on 2017/9/4.
 * email : huangjinping@hdfex.com
 */

public class RegisterActivity extends BaseActivity {


    @Bind(R.id.edt_phonenumber)
    EditText edtPhonenumber;
    @Bind(R.id.edt_verfication_code)
    EditText edtVerficationCode;
    @Bind(R.id.bt_verification_code)
    Button btVerificationCode;
    @Bind(R.id.edt_password)
    EditText edtPassword;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.txt_login)
    TextView txtLogin;
    @Bind(R.id.txt_nations)
    TextView txtNations;
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    private CountDownTimer timer;

    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        txtLogin.setText(Html.fromHtml("<U>去登陆</U>"));
        navBar.setTitleText("注册");
        RxBus.getInstance().register("area").subscribe(new Subscriber<Object>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {
                Nations nations = (Nations) o;
                txtNations.setText(nations.getNationCode());
            }
        });
    }


    @OnClick(R.id.bt_verification_code)
    public void onLoadVerification() {
        String phoneNumber = edtPhonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            showLongToast("请输入手机号码");
            return;
        }
        startProgressDialog();

        OkHttpUtils.post().url(HostConstants.SEND_SMS_CODE)
                .addParams("type", "register")
                .addParams("mobile", phoneNumber)
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

                SmsCode smsCode = GsonUtil.changeGsonToBean(response, SmsCode.class);
                if (isOkCode(smsCode.getCode(), smsCode.getMessage())) {
                    timer = new CountDownTimer(80000, 1000) {

                        @Override
                        public void onTick(long millisUntilFinished) {
                            int i = (int) (millisUntilFinished / 1000);
                            if (btVerificationCode != null) {
                                btVerificationCode.setText(i + "秒后重新获取");
                                btVerificationCode.setClickable(false);
                            }

                        }

                        @Override
                        public void onFinish() {

                            if (btVerificationCode != null) {
                                btVerificationCode.setText("获取验证码");
                                btVerificationCode.setClickable(true);
                                btVerificationCode.setSelected(false);
                                btVerificationCode.setClickable(true);

                            }

                        }
                    };
                    timer.start();
                }


            }
        });
    }

    @OnClick(R.id.btn_submit)
    public void onSubmit() {

        String phoneNumber = edtPhonenumber.getText().toString().trim();
        if (TextUtils.isEmpty(phoneNumber)) {
            showLongToast("请输入手机号码");
            return;
        }
        String smsCode = edtVerficationCode.getText().toString().trim();
        if (TextUtils.isEmpty(smsCode)) {
            showLongToast("请输入验证码");
            return;
        }

        String password = edtPassword.getText().toString().trim();
        if (TextUtils.isEmpty(password)) {
            showLongToast("请输入登录密码");
            return;
        }

        startProgressDialog();
        OkHttpUtils.post().url(HostConstants.REGISTER)
                .addParams("type", "register")
                .addParams("phone", phoneNumber)
                .addParams("nationCode", txtNations.getText().toString().trim())
                .addParams("smsCode", smsCode)
                .addParams("platform", "adnroid")
                .addParams("password", password)
//                .addParams("password", Md5Security.getMD5(password))
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
                RegisterResponse registerResponse = GsonUtil.changeGsonToBean(response, RegisterResponse.class);
                if (isOkCode(registerResponse.getCode(), registerResponse.getMessage())) {
                    UserManager.saveUser(mContext, registerResponse.getResult());
                    toMain();
                    return;
                }
            }

            private void toMain() {
                Intent intent = new Intent(mContext, MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }


        });
    }

    /**
     * 打开注册界面
     *
     * @param context
     */
    public static void startAction(Context context) {
        Intent intent = new Intent(context, RegisterActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.txt_login)
    public void toLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.txt_nations)
    public void toGetAction() {
        SelectAreaActivity.startAction(mContext);
    }


}

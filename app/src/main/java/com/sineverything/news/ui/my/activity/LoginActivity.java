package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.main.RegisterResponse;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.activity.MainActivity;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/6.
 * email : huangjinping@hdfex.com
 */

public class LoginActivity extends BaseActivity {

    @Bind(R.id.img_cancel)
    ImageView imgCancel;
    @Bind(R.id.edt_login_name)
    EditText edtLoginName;
    @Bind(R.id.edt_login_password)
    EditText edtLoginPassword;
    @Bind(R.id.txt_forget_password)
    TextView txtForgetPassword;
    @Bind(R.id.btn_login)
    Button btnLogin;
    @Bind(R.id.txt_fast_register)
    TextView txtFastRegister;


    public static void startAction(Context context) {
        Intent intent = new Intent(context, LoginActivity.class);
        context.startActivity(intent);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        txtFastRegister.setText(Html.fromHtml("<U>快速注册</U>"));
    }


    @OnClick(R.id.btn_login)
    public void onLogin() {
        String name = edtLoginName.getText().toString().trim();
        if (TextUtils.isEmpty(name)) {
            showLongToast("请输入手机号");
            return;
        }
        String psssword = edtLoginPassword.getText().toString().trim();
        if (TextUtils.isEmpty(psssword)) {
            showLongToast("请输入密码");
            return;
        }
        startProgressDialog();
        OkHttpUtils.post().url(HostConstants.LOGIN)
                .addParams("mobile", name)
                .addParams("phone", name)
                .addParams("password", psssword)
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
                RegisterResponse registerReponse = GsonUtil.changeGsonToBean(response, RegisterResponse.class);
                if (isOkCode(registerReponse.getCode(), registerReponse.getMessage())) {
                    UserManager.saveUser(mContext,registerReponse.getResult());
                    toMain();
                    return;
                }
            }
        });
    }


    private void toMain() {
        Intent intent=new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @OnClick(R.id.txt_fast_register)
    public void onRegister() {
        RegisterActivity.startAction(this);
    }


}

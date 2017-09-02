package com.sineverything.news.ui.my.activity;

import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;

import butterknife.Bind;
import butterknife.ButterKnife;

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


}

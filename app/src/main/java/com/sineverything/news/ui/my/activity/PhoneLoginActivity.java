package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/5/21.
 * email : huangjinping@hdfex.com
 */

public class PhoneLoginActivity extends BaseActivity {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.edit_login_phone)
    EditText editLoginPhone;
    @Bind(R.id.bt_verification_code)
    Button btVerificationCode;
    @Bind(R.id.edit_verification_code)
    EditText editVerificationCode;

    @Override
    public int getLayoutId() {
        return R.layout.act_phonelogin;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ntb.setTitleText(R.string.phone_login_title);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, PhoneLoginActivity.class);
        context.startActivity(intent);
    }
}

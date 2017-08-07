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
}

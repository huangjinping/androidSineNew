package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.comm.UserManager;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/9/11.
 * email : huangjinping@hdfex.com
 */

public class SettingActivity extends BaseActivity {

    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.txt_version_name)
    TextView txtVersionName;
    @Bind(R.id.rl_setting)
    RelativeLayout rlSetting;
    @Bind(R.id.btn_login_out)
    Button btnLoginOut;

    @Override
    public int getLayoutId() {
        return R.layout.activity_setting;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        navBar.setTitleText("设置");
        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtVersionName.setText(getAppVersionName(this));
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, SettingActivity.class);
        context.startActivity(intent);
    }


    public static String getAppVersionName(Context context) {
        String versionName = "";
        try {
            // ---get the package info---
            PackageManager pm = context.getPackageManager();
            PackageInfo pi = pm.getPackageInfo(context.getPackageName(), 0);
            versionName = pi.versionName;
            if (versionName == null || versionName.length() <= 0) {
                return "";
            }
        } catch (Exception e) {
        }
        return versionName;
    }

    @OnClick(R.id.btn_login_out)
    public void logout() {
        if (UserManager.isLogin(this)) {
            UserManager.logout(this);
            finish();
        }
    }
}

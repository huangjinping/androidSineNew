package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;

/**
 * author Created by harrishuang on 2017/8/13.
 * email : huangjinping@hdfex.com
 */

public class HelpCenterActivity  extends BaseActivity{

    @Override
    public int getLayoutId() {
        return R.layout.activity_helpcenter;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, HelpCenterActivity.class);
        context.startActivity(intent);
    }
}

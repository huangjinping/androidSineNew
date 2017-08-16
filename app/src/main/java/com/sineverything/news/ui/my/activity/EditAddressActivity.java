package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;

/**
 * author Created by harrishuang on 2017/8/12.
 * email : huangjinping@hdfex.com
 */

public class EditAddressActivity extends BaseActivity {

    @Override
    public int getLayoutId() {
        return R.layout.activity_editaddress;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    public static void startAction(Context context){
        Intent intent=new Intent(context,EditAddressActivity.class);
        context.startActivity(intent);
    }
}

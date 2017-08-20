package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;

/**
 * author Created by harrishuang on 2017/8/15.
 * email : huangjinping@hdfex.com
 */

public class SearchCommodityActivity extends BaseActivity {


    @Override
    public int getLayoutId() {
        return R.layout.activity_searchcommodity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SearchCommodityActivity.class);
        context.startActivity(intent);
    }
}

package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.bean.order.OrderDetails;

/**
 * author Created by harrishuang on 2017/8/15.
 * email : huangjinping@hdfex.com
 */

public class OrderDetailsActivity extends BaseActivity {




    public static void startAction(Context context,OrderDetails result){
        Intent intent=new Intent(context,OrderDetailsActivity.class);
        intent.putExtra(OrderDetails.class.getSimpleName(),result);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {

        return R.layout.act_orderdetails;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }
}

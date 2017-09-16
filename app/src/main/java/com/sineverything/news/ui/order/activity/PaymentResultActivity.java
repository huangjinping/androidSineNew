package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.ui.main.activity.MainActivity;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/9/14.
 * email : huangjinping@hdfex.com
 */

public class PaymentResultActivity extends BaseActivity {
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.btn_order)
    Button btnOrder;

    @Override
    public int getLayoutId() {
        return R.layout.activity_paymentresult;
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.btn_order)
    public void onOrderList(){
        OrderListActivity.startAction(this);
    }


    @Override
    public void initView() {
        navBar.setTitleText("支付成功");
        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
                finish();
            }
        });
    }



    private void toMain() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, PaymentResultActivity.class);
        context.startActivity(intent);
    }
}

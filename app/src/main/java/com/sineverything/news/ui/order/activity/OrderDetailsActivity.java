package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.bean.order.GoodsInfo;
import com.sineverything.news.bean.order.OrderDetails;
import com.sineverything.news.ui.order.adapter.OrderDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/8/15.
 * email : huangjinping@hdfex.com
 */

public class OrderDetailsActivity extends BaseActivity {


    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.rec_order_details)
    RecyclerView recOrderDetails;
    @Bind(R.id.txt_return)
    TextView txtReturn;
    @Bind(R.id.txt_payment)
    TextView txtPayment;
    private OrderDetailsAdapter detailsAdapter;
    private List<GoodsInfo> dataList;
    private OrderDetails details;


    public static void startAction(Context context, OrderDetails result) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra(OrderDetails.class.getSimpleName(), result);
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
        navBar.setTitleText("订单详情");
        Intent intent = getIntent();
        dataList = new ArrayList<>();
        details = (OrderDetails) intent.getSerializableExtra(OrderDetails.class.getSimpleName());
        List<GoodsInfo> goodsInfo = details.getGoodsInfo();
        dataList.addAll(goodsInfo);
        detailsAdapter = new OrderDetailsAdapter(dataList, details);
        recOrderDetails.setLayoutManager(new LinearLayoutManager(this));
        recOrderDetails.setAdapter(detailsAdapter);
//        status   10 待付款  20 代发货 30 待收货 40 待评价 50 已完成 0 已取消




        if (!TextUtils.isEmpty(details.getOrderStatus())) {
            String status = details.getOrderStatus();
            if ("10".equals(status)) {
                txtPayment.setVisibility(View.VISIBLE);
            } else if ("30".equals(status)) {
                txtReturn.setVisibility(View.VISIBLE);
            }
        }
        txtPayment.setVisibility(View.VISIBLE);
        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick(R.id.txt_payment)
    public void onPay() {
        PaymentActivity.startAction(this, details.getOrderId(), details.getTotalPrice());
    }


}

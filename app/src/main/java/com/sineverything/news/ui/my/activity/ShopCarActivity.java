package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.ui.my.adapter.ShopCarAdapter;
import com.sineverything.news.ui.order.activity.ConfirmOrderActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/8/9.
 * email : huangjinping@hdfex.com
 */

public class ShopCarActivity extends BaseActivity {


    @Bind(R.id.rec_shop_car)
    RecyclerView recShopCar;
    @Bind(R.id.txt_settlement)
    TextView txtSettlement;
    private ShopCarAdapter mAdapter;
    private List<String> dataList;


    @Override
    public int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dataList = new ArrayList<>();
        mAdapter = new ShopCarAdapter(dataList);
        recShopCar.setLayoutManager(new LinearLayoutManager(this));
        recShopCar.setAdapter(mAdapter);
        for (int i = 0; i < 100; i++) {
            dataList.add("");
        }
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 提交
     *
     * @param context
     */
    public static void startAction(Context context) {
        Intent intent = new Intent(context, ShopCarActivity.class);
        context.startActivity(intent);
    }

    @OnClick(R.id.txt_settlement)
    public void toPay() {
//        ConfirmOrderActivity.startAction(this);

    }
}

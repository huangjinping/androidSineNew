package com.sineverything.news.ui.order.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.ui.order.adapter.ConfirmOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/7.
 * email : huangjinping@hdfex.com
 */

public class ConfirmOrderActivity extends BaseActivity {

    @Bind(R.id.rec_confirm_order)
    RecyclerView recConfirmOrder;
    private List<String> dataList;
    private ConfirmOrderAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirmorder;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("");
        }
        adapter = new ConfirmOrderAdapter(dataList);
        recConfirmOrder.setLayoutManager(new LinearLayoutManager(this));
        recConfirmOrder.setAdapter(adapter);
    }


}

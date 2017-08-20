package com.sineverything.news.ui.order.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaydenxiao.common.base.BaseFragment;
import com.sineverything.news.R;
import com.sineverything.news.ui.order.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class OrderListFragment extends BaseFragment {


    @Bind(R.id.rec_order_list)
    RecyclerView recOrderList;
    private OrderListAdapter orderListAdapter;
    private List<String>  dataList;



    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dataList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            dataList.add("");
        }
        orderListAdapter=new OrderListAdapter(dataList);
        recOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        recOrderList.setAdapter(orderListAdapter);
    }


}

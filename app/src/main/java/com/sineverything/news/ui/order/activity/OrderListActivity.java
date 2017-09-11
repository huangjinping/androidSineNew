package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.ui.order.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class OrderListActivity extends BaseActivity {

    @Bind(R.id.tab_layout_header)
    SlidingTabLayout tab_layout_header;
    @Bind(R.id.vip_order)
    ViewPager vipOrder;
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    private List<Fragment> fragmentList;
    private BaseFragmentAdapter mAdapter;
    private List<String> titleList;

    //    status   10 待付款  20 代发货 30 待收货 40 待评价 50 已完成 0 已取消
    private String[] title = {"待付款", "代发货", "待收货", "待评价", "已完成", "已取消"};
    private String[] status = {"10", "20", "30", "40", "50", "0"};


    @Override
    public int getLayoutId() {
        return R.layout.act_orderlist;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        navBar.setTitleText("订单");
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList = Arrays.asList(title);
        for (int i = 0; i < title.length; i++) {
            fragmentList.add(OrderListFragment.getInstance(status[i]));
        }
        mAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        vipOrder.setAdapter(mAdapter);
        tab_layout_header.setViewPager(vipOrder);
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, OrderListActivity.class);
        context.startActivity(intent);
    }


}

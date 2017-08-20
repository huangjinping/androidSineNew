package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.sineverything.news.R;
import com.sineverything.news.ui.main.fragment.MainChildFragment;
import com.sineverything.news.ui.order.fragment.OrderListFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class OrderListActivity extends BaseActivity {

    @Bind(R.id.tab_layout_header)
    SlidingTabLayout tab_layout_header;
    @Bind(R.id.vip_order)
    ViewPager vipOrder;
    private List<Fragment> fragmentList;
    private BaseFragmentAdapter mAdapter;
    private List<String> titleList;

    @Override
    public int getLayoutId() {
        return R.layout.act_orderlist;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            titleList.add("d"+i);
            fragmentList.add(new OrderListFragment());
        }
        mAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        vipOrder.setAdapter(mAdapter);
        tab_layout_header.setViewPager(vipOrder);
    }


    public static void startAction(Context context) {
        Intent intent=new Intent(context,OrderListActivity.class);
        context.startActivity(intent);
    }
}

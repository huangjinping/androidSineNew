package com.sineverything.news.ui.service.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.ui.service.fragment.ServiceListFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class ServiceListActivity extends BaseActivity {

    @Bind(R.id.tab_layout_header)
    SlidingTabLayout tab_layout_header;
    @Bind(R.id.vip_order)
    ViewPager vipOrder;
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    private List<Fragment> fragmentList;
    private BaseFragmentAdapter mAdapter;
    private List<String> titleList;
    private String type;
    private String name;


    //    status   10 待付款  20 代发货 30 待收货 40 待评价 50 已完成 0 已取消
    private String[] title = {"全部", "附近", "智能排序", "筛选"};


    @Override
    public int getLayoutId() {
        return R.layout.act_servicelist;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        titleList = new ArrayList<>();
        fragmentList = new ArrayList<>();
        titleList = Arrays.asList(title);
        Intent intent = getIntent();
        type = intent.getStringExtra("type");
        name = intent.getStringExtra("name");
        navBar.setTitleText(name);
//        for (int i = 0; i < title.length; i++) {
            fragmentList.add(ServiceListFragment.getInstance(type));
//        }
        mAdapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, titleList);
        vipOrder.setAdapter(mAdapter);
        tab_layout_header.setViewPager(vipOrder);
    }


    public static void startAction(Context context, String type, String name) {
        Intent intent = new Intent(context, ServiceListActivity.class);
        intent.putExtra("type", type);
        intent.putExtra("name", name);
        context.startActivity(intent);
    }

}

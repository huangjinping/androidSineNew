package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.flyco.tablayout.SlidingTabLayout;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.base.BaseFragmentAdapter;
import com.sineverything.news.R;
import com.sineverything.news.ui.my.fragment.CollectionCommodityFragment;
import com.sineverything.news.ui.my.fragment.CollectionNewFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/8/12.
 * email : huangjinping@hdfex.com
 */

public class CollectionActivity extends BaseActivity {


    private final String[] mTitles = {
            "商品", "新闻"
    };

    @Bind(R.id.tab_layout_collection)
    SlidingTabLayout tabLayoutCollection;
    @Bind(R.id.vip_conllection)
    ViewPager vipConllection;
    private List<Fragment> fragmentList;

    private BaseFragmentAdapter adapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_collection;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(CollectionCommodityFragment.getInstance());
        fragmentList.add(CollectionNewFragment.getInstance());
        List<String> listA = Arrays.asList(mTitles);
        List<String> listB = new ArrayList<String>(listA);
        adapter = new BaseFragmentAdapter(getSupportFragmentManager(), fragmentList, listB);
        vipConllection.setAdapter(adapter);
        tabLayoutCollection.setViewPager(vipConllection, mTitles);
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, CollectionActivity.class);
        context.startActivity(intent);
    }


}

package com.sineverything.news.ui.main.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.ui.main.fragment.GuideFragment;

import java.util.ArrayList;

import butterknife.Bind;


/**
 * 引导页
 * Created by harrishuang on 16/9/28.
 */

public class GuideActivity extends BaseActivity {

    @Bind(R.id.vip_guide)
    ViewPager vipGuide;
    private ArrayList<Fragment> fragmentsList;
    private OrderPagerAdapter adapter;


    @Override
    public int getLayoutId() {
        return R.layout.activity_guide;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        fragmentsList = new ArrayList<>();
        fragmentsList.add(GuideFragment.getInstance(0));
        fragmentsList.add(GuideFragment.getInstance(1));
        fragmentsList.add(GuideFragment.getInstance(2));
        adapter = new OrderPagerAdapter(getSupportFragmentManager());
        vipGuide.setAdapter(adapter);
    }








    /**
     * 引导页适配器
     */
    private class OrderPagerAdapter extends FragmentPagerAdapter {
        public OrderPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentsList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {

            return position + "";
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentsList.get(position);
        }
    }

}

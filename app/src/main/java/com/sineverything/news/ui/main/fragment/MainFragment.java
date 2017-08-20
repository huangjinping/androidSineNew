package com.sineverything.news.ui.main.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.widget.ImageView;

import com.flyco.tablayout.SlidingTabLayout;
import com.jaydenxiao.common.base.BaseFragment;
import com.sineverything.news.R;
import com.sineverything.news.ui.main.activity.EditActivity;
import com.sineverything.news.ui.main.activity.SearchNewsActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class MainFragment extends BaseFragment {
    @Bind(R.id.tab_layout_header)
    SlidingTabLayout tabLayoutHeader;
    @Bind(R.id.vip_news)
    ViewPager vipNews;
    @Bind(R.id.img_search)
    ImageView imgSearch;
    @Bind(R.id.img_edit)
    ImageView imgEdit;
    private List<BaseFragment> fragmentList;

    private final String[] mTitles = {
            "新加坡", "天下"
    };

    private NewsPagerAdapter adapter;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_main;
    }

    @Override
    public void initPresenter() {

    }

    @OnClick(R.id.img_search)
    public void onSearch(){
        SearchNewsActivity.startAction(getActivity());
    }


    @OnClick(R.id.img_edit)
    public void onEdit(){
        EditActivity.startAction(getActivity());
    }








    @Override
    protected void initView() {
        fragmentList = new ArrayList<>();
        fragmentList.add(MainChildFragment.getInstance("1"));
        fragmentList.add(MainChildFragment.getInstance("1"));
        adapter = new NewsPagerAdapter(getChildFragmentManager());
        vipNews.setAdapter(adapter);
        tabLayoutHeader.setViewPager(vipNews, mTitles);
    }


    /**
     *
     */
    private class NewsPagerAdapter extends FragmentPagerAdapter {
        public NewsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mTitles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }
    }


}

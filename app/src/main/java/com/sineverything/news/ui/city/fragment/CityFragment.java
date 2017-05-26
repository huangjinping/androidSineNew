package com.sineverything.news.ui.city.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class CityFragment extends BaseFragment {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_city;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {

        ntb.setBackVisibility(false);
        ntb.setTitleText(R.string.city);
    }

}

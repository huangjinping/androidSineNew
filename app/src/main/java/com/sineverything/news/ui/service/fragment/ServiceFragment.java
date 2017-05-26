package com.sineverything.news.ui.service.fragment;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class ServiceFragment extends BaseFragment {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_service;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        ntb.setBackVisibility(false);
        ntb.setTitleText(R.string.service);

    }


}

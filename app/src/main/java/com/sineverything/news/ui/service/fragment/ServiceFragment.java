package com.sineverything.news.ui.service.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.ui.service.adapter.ServiceAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class ServiceFragment extends BaseFragment {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.rec_service)
    RecyclerView recService;
    private ServiceAdapter adapter;
    private List<String> dataList;


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
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add("");
        }
        adapter = new ServiceAdapter(dataList);
        recService.setLayoutManager(new LinearLayoutManager(getActivity()));
        recService.setAdapter(adapter);
    }
}

package com.sineverything.news.ui.my.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseFragment;
import com.sineverything.news.R;
import com.sineverything.news.ui.my.adapter.CollectionNewAdappter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/12.
 * email : huangjinping@hdfex.com
 */

public class CollectionNewFragment extends BaseFragment {

    @Bind(R.id.rec_collection)
    RecyclerView recCollection;
    private CollectionNewAdappter mAdapter;
    private List<String> dataList;

    public static BaseFragment getInstance(){
        CollectionNewFragment fragment=new CollectionNewFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_collectionnew;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("");
        }
        mAdapter = new CollectionNewAdappter(dataList);
        recCollection.setLayoutManager(new LinearLayoutManager(getActivity()));
        recCollection.setAdapter(mAdapter);
    }
}

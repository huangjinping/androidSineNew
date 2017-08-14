package com.sineverything.news.ui.my.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseFragment;
import com.sineverything.news.R;
import com.sineverything.news.ui.commodity.adapter.CommodityListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/12.
 * email : huangjinping@hdfex.com
 */

public class CollectionCommodityFragment extends BaseFragment {

    @Bind(R.id.rec_collection)
    RecyclerView recCollection;
    private CommodityListAdapter mAdapter;
    private List<String> dataList;
    public static BaseFragment getInstance(){
        CollectionCommodityFragment fragment=new CollectionCommodityFragment();
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_collectioncommdity;
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
        mAdapter = new CommodityListAdapter(dataList);
        recCollection.setLayoutManager(new LinearLayoutManager(getActivity()));
        recCollection.setAdapter(mAdapter);
    }
}

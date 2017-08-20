package com.sineverything.news.ui.commodity.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseFragment;
import com.sineverything.news.R;
import com.sineverything.news.ui.commodity.adapter.ClassifyContentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/20.
 * email : huangjinping@hdfex.com
 */

public class ClassifyFragment extends BaseFragment {


    @Bind(R.id.rec_content)
    RecyclerView recContent;
    private ClassifyContentAdapter mAdapter;
    private List<String> dataList;
    public String type;

    public static Fragment getInstance(String type) {
        ClassifyFragment fragment = new ClassifyFragment();
        fragment.type = type;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_classify;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            dataList.add("");
        }
        mAdapter = new ClassifyContentAdapter(dataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {
                return position==0?3:1;
            }
        });
        recContent.setLayoutManager(gridLayoutManager);
        recContent.setAdapter(mAdapter);
    }
}

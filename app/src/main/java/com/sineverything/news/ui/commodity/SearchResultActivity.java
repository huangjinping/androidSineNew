package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.ui.commodity.adapter.CommodityListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/15.
 * email : huangjinping@hdfex.com
 */

public class SearchResultActivity extends BaseActivity {


    @Bind(R.id.rec_search_commodity)
    RecyclerView recSearchCommodity;
    private CommodityListAdapter adapter;
    private List<String> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_resultcommodity;
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
        adapter = new CommodityListAdapter(dataList);
        recSearchCommodity.setLayoutManager(new LinearLayoutManager(this));
        recSearchCommodity.setAdapter(adapter);
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        context.startActivity(intent);
    }
}

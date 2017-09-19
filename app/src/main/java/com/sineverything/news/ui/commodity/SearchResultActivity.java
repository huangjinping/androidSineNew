package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Goods;
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
    private List<Goods> dataList;

    @Override
    public int getLayoutId() {
        return R.layout.activity_resultcommodity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {

    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, SearchResultActivity.class);
        context.startActivity(intent);
    }

    public static void startActionWithKeywords(Context mContext, String key) {


    }
}

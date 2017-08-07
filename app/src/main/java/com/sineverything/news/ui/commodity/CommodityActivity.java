package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.comm.TabEntity;
import com.sineverything.news.ui.commodity.adapter.CommodityListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/5.
 * email : huangjinping@hdfex.com
 */

public class CommodityActivity extends BaseActivity {

    @Bind(R.id.ic_left_icon)
    ImageView icLeftIcon;
    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.txt_action)
    TextView txtAction;
    @Bind(R.id.layout_filter)
    CommonTabLayout layoutFilter;
    @Bind(R.id.rec_commodity_list)
    RecyclerView recCommodityList;
    private String[] mTitles = {"综合", "销量", "价格"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<String> dataList;
    private CommodityListAdapter adapter;


    private int[] mIconSelectIds = {
            R.mipmap.edit_search_down, R.mipmap.edit_search_down,
            R.mipmap.edit_search_down, R.mipmap.edit_search_down};
    private int[] mIconUnselectIds = {
            R.mipmap.edit_search_down, R.mipmap.edit_search_down,
            R.mipmap.edit_search_down, R.mipmap.edit_search_down};

    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity;
    }

    @Override
    public void initPresenter() {


    }

    @Override
    public void initView() {

        String[] title = {"综合", "销量", "价格"};
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }

        layoutFilter.setTabData(mTabEntities);

        dataList=new ArrayList<>();
        for (int i = 0; i <100 ; i++) {
            dataList.add("");
        }
        adapter=new CommodityListAdapter(dataList);
        recCommodityList.setLayoutManager(new LinearLayoutManager(this));
        recCommodityList.setAdapter(adapter);
    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, CommodityActivity.class);
        context.startActivity(intent);
    }
}

package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.ui.my.adapter.AddressAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/8.
 * email : huangjinping@hdfex.com
 */

public class AddressActivity extends BaseActivity {



    @Bind(R.id.rec_address)
    RecyclerView recAddress;
    private List<String> dataList;
    private AddressAdapter adapter;
    @Override
    public int getLayoutId() {
        return R.layout.activity_address;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dataList=new ArrayList<>();
        for (int i = 0; i <10 ; i++) {
            dataList.add("");
        }
        adapter=new AddressAdapter(dataList);
        recAddress.setLayoutManager(new LinearLayoutManager(this));
        recAddress.setAdapter(adapter);
    }


    public static void startAction(Context context) {
        Intent intent=new Intent(context,AddressActivity.class);
        context.startActivity(intent);
    }
}

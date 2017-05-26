package com.sineverything.news.ui.main.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.bumptech.glide.Glide;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.sineverything.news.R;
import com.sineverything.news.ui.my.adapter.EditAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/5/21.
 * email : huangjinping@hdfex.com
 */

public class EditActivity extends BaseActivity {
    @Bind(R.id.ntb)
    NormalTitleBar ntb;
    @Bind(R.id.rl_edit)
    RecyclerView rlEdit;

    private EditAdapter editAdapter;
    private List<String> dataList;


    @Override
    public int getLayoutId() {
        return R.layout.atc_edit;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        ntb.setTitleText(R.string.select_type);
        ntb.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        dataList=new ArrayList<>();
        for (int i = 0; i <13 ; i++) {
            dataList.add("信息"+i);
        }
        editAdapter=new EditAdapter(dataList);
        rlEdit.setLayoutManager(new GridLayoutManager(this,4));
        rlEdit.setAdapter(editAdapter);
    }

    public static void startAction(Context context) {
        Intent intent = new Intent(context, EditActivity.class);
        context.startActivity(intent);
    }


}

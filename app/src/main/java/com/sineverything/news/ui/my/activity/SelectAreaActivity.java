package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.view.widget.MyLetterListView;
import com.sineverything.news.R;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/9/2.
 * email : huangjinping@hdfex.com
 */

public class SelectAreaActivity extends BaseActivity {


    @Bind(R.id.ic_nav)
    NormalTitleBar icNav;
    @Bind(R.id.sh)
    EditText sh;
    @Bind(R.id.list_view)
    ListView listView;
    @Bind(R.id.search_result)
    ListView searchResult;
    @Bind(R.id.tv_noresult)
    TextView tvNoresult;
    @Bind(R.id.letterListView)
    MyLetterListView letterListView;




    public  static void startAction(Context context){
        Intent intent=new Intent(context,SelectAreaActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_selectarea;
    }

    @Override
    public void initPresenter() {

    }


    @Override
    public void initView() {
        letterListView.setOnTouchingLetterChangedListener(new MyLetterListView.OnTouchingLetterChangedListener() {
            @Override
            public void onTouchingLetterChanged(String s) {
                showLongToast(s + "");
            }
        });
    }


}

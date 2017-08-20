package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.jaydenxiao.common.base.BaseActivity;
import com.sineverything.news.R;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.commodity.adapter.MenuAdapter;
import com.sineverything.news.ui.commodity.fragment.ClassifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class ClassifyActivity extends BaseActivity {


    @Bind(R.id.rec_menu)
    RecyclerView recMenu;
    @Bind(R.id.layout_menu_content)
    LinearLayout layoutMenuContent;
    private MenuAdapter menuAdapter;
    private List<String> menuList;
    private FragmentManager supportFragmentManager;

    @Override
    public int getLayoutId() {
        return R.layout.act_classify;
    }

    @Override
    public void initPresenter() {


    }

    @Override
    public void initView() {
        supportFragmentManager = getSupportFragmentManager();
        menuList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            menuList.add("=" + i);
        }
        menuAdapter = new MenuAdapter(menuList);
        recMenu.setLayoutManager(new LinearLayoutManager(this));
        recMenu.setAdapter(menuAdapter);
        menuAdapter.setItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                setFragmentByType(postion + "");
            }
        });

    }


    private void setFragmentByType(String type) {
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_menu_content, ClassifyFragment.getInstance(type));
        fragmentTransaction.commit();

    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, ClassifyActivity.class);
        context.startActivity(intent);
    }
}

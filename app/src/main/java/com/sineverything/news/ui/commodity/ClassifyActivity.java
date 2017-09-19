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
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.commodity.MenuItem;
import com.sineverything.news.bean.commodity.MenuResponse;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.commodity.adapter.MenuAdapter;
import com.sineverything.news.ui.commodity.fragment.ClassifyFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

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
    private List<MenuItem> menuList;
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

        menuAdapter = new MenuAdapter(menuList);
        recMenu.setLayoutManager(new LinearLayoutManager(this));
        recMenu.setAdapter(menuAdapter);
        menuAdapter.setItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {

                setFragmentByType(postion);
            }
        });

        loadMenu();
    }


    private void setFragmentByType(int postion) {
        for (int i = 0; i <menuList.size() ; i++) {
            menuList.get(i).setSelected(false);
        }
        menuList.get(postion).setSelected(true);
        menuAdapter.notifyDataSetChanged();
        MenuItem item = menuList.get(postion);
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.layout_menu_content, ClassifyFragment.getInstance(item.getId()));
        fragmentTransaction.commit();

    }


    public static void startAction(Context context) {
        Intent intent = new Intent(context, ClassifyActivity.class);
        context.startActivity(intent);
    }


    /**
     * 一级菜单
     */
    private void loadMenu() {
        startProgressDialog();
        OkHttpUtils.post().url(HostConstants.LEFT_CATEGOGORIES)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
            }

            @Override
            public void onAfter() {
                super.onAfter();
                stopProgressDialog();

            }

            @Override
            public void onResponse(String response) {
                MenuResponse menuResponse = GsonUtil.changeGsonToBean(response, MenuResponse.class);


                if (menuResponse != null) {
                    if (isOkCode(menuResponse.getCode(), menuResponse.getMessage())) {
                        // 成功
                        menuList.clear();
                        menuList.addAll(menuResponse.getResult());

                        if (menuList.size() > 0) {
                            MenuItem item = menuList.get(0);
                            setFragmentByType(0);
                        }

                        menuAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

}

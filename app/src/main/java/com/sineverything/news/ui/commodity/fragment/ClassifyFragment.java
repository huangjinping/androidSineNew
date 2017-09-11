package com.sineverything.news.ui.commodity.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.bean.commodity.MenuItem;
import com.sineverything.news.bean.commodity.MenuResponse;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.commodity.CommodityActivity;
import com.sineverything.news.ui.commodity.CommodityDetailsActivity;
import com.sineverything.news.ui.commodity.adapter.ClassifyContentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/20.
 * email : huangjinping@hdfex.com
 */

public class ClassifyFragment extends BaseFragment {

    @Bind(R.id.rec_content)
    RecyclerView recContent;
    private ClassifyContentAdapter mAdapter;
    private List<MenuItem> dataList;
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
        mAdapter = new ClassifyContentAdapter(dataList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
//        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
//            @Override
//            public int getSpanSize(int position) {
//                return position == 0 ? 3 : 1;
//            }
//        });

        mAdapter.setItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                MenuItem goods = dataList.get(postion);
                CommodityActivity.startActionWithId(getActivity(),goods.getId());

            }
        });
        recContent.setLayoutManager(gridLayoutManager);
        recContent.setAdapter(mAdapter);
        loadChildMenu(type);
    }

    /**
     * 一级菜单
     */
    private void loadChildMenu(String classId) {
        startProgressDialog();
        OkHttpUtils.post()
                .url(HostConstants.CATEGORIES_BY_PID)
                .addParams("classId", classId)
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
                        dataList.clear();
                        dataList.addAll(menuResponse.getResult());
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}

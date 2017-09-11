package com.sineverything.news.ui.main.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.andview.refreshview.XRefreshView;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.main.Banner;
import com.sineverything.news.bean.main.BannerResponse;
import com.sineverything.news.bean.main.NewsItem;
import com.sineverything.news.bean.main.NewsItemResponse;
import com.sineverything.news.ui.main.adpater.MainNewsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class MainChildFragment extends BaseFragment {


    @Bind(R.id.rec_main)
    RecyclerView recMain;
    MainNewsAdapter adapter;
    @Bind(R.id.xr_freshview)
    XRefreshView xrFreshview;
    private List<NewsItem> dataList;
    private List<Banner> banners;
    public String type;


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_mainchild;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        xrFreshview.setPullRefreshEnable(true);
        xrFreshview.setPullLoadEnable(true);
        xrFreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                super.onRefresh();
            }

            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                loadData(LoadMode.NOMAL);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                loadData(LoadMode.PULL_REFRSH);
            }

            @Override
            public void onRelease(float direction) {
                super.onRelease(direction);
            }

            @Override
            public void onHeaderMove(double offset, int offsetY) {
                super.onHeaderMove(offset, offsetY);
            }
        });

        dataList = new ArrayList<>();
        banners = new ArrayList<>();
        adapter = new MainNewsAdapter(dataList, banners);
        recMain.setLayoutManager(new LinearLayoutManager(getContext()));
        recMain.setAdapter(adapter);
        loadData(LoadMode.NOMAL);
        getIndexBanner();

    }


    public static BaseFragment getInstance(String s) {
        MainChildFragment fragment = new MainChildFragment();
        fragment.type = s;
        return fragment;
    }


    /**
     * 下载中央数据
     *
     * @param loadMode
     */
    private int page = 1;

    private void loadData(final LoadMode loadMode) {
        if (loadMode == LoadMode.NOMAL) {

        }
        if (loadMode != LoadMode.UP_REFRESH) {
            page = 1;
        }
        OkHttpUtils.post()
                .url(HostConstants.NEWSLIST)
                .addParams("type", type)
                .addParams("pageSize", "20")
                .addParams("pageIndex", page + "")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                xrFreshview.stopRefresh();
                                xrFreshview.stopLoadMore();
                            }
                        });

                    }

                    @Override
                    public void onError(Call call, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            NewsItemResponse newsItemResponse = GsonUtil.changeGsonToBean(response, NewsItemResponse.class);

                            if (isOkCode(newsItemResponse.getCode(), newsItemResponse.getMessage())) {

                                List<NewsItem> result = newsItemResponse.getResult();
                                if (loadMode != LoadMode.UP_REFRESH) {
                                    dataList.clear();
                                    page++;
                                }
                                dataList.addAll(result);
                                adapter.notifyDataSetChanged();
                            }


                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                });
    }


    /**
     * 获取banner表
     */
    private void getIndexBanner() {

        OkHttpUtils.post()
                .url(HostConstants.GET_INDEX_BANNDER)
                .addParams("type", type)
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


            }

            @Override
            public void onResponse(String response) {
                BannerResponse bannerResponse = GsonUtil.changeGsonToBean(response, BannerResponse.class);

                if (bannerResponse != null) {
                    if (isOkCode(bannerResponse.getCode(), bannerResponse.getMessage())) {
                        // 成功
                        banners.clear();
                        banners.addAll(bannerResponse.getResult());
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.unbind(this);
    }
}

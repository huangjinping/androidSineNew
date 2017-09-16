package com.sineverything.news.ui.service.fragment;

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
import com.sineverything.news.bean.main.NewsItem;
import com.sineverything.news.bean.main.NewsItemResponse;
import com.sineverything.news.comm.widget.MultiStateView;
import com.sineverything.news.ui.main.adpater.SearchAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/9/6.
 * email : huangjinping@hdfex.com
 */

public class ServiceListFragment extends BaseFragment {


    @Bind(R.id.rec_service)
    RecyclerView recService;
    @Bind(R.id.xr_freshview)
    XRefreshView xrFreshview;
    @Bind(R.id.multiStateView)
    MultiStateView multiStateView;
    private List<NewsItem> dataList;
    private SearchAdapter mAdapter;
    public String classId;


    public static BaseFragment getInstance(String classId) {
        ServiceListFragment fragment = new ServiceListFragment();
        fragment.classId = classId;

        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.frgment_service_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        dataList = new ArrayList<>();
        mAdapter = new SearchAdapter(dataList);
        recService.setLayoutManager(new LinearLayoutManager(getActivity()));
        recService.setAdapter(mAdapter);
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
        loadData(LoadMode.NOMAL);
    }


    /**
     * 下载中央数据
     *
     * @param
     */
    private int page = 1;

    private void loadData(final LoadMode loadMode) {

        if (loadMode != LoadMode.UP_REFRESH) {
            page = 1;
        }
        OkHttpUtils.post()
                .url(HostConstants.SERVICE_INFO_LIST)
                .addParams("pageSize", 30 + "")
                .addParams("pageIndex", page + "")
                .addParams("classId", classId)
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
                        multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);

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
                                mAdapter.notifyDataSetChanged();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }
                        if (dataList.size() == 0) {
                            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);

                        } else {
                            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                        }


                    }
                });
    }



}

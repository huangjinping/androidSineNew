package com.sineverything.news.ui.order.fragment;

import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.order.Order;
import com.sineverything.news.bean.order.OrderDetails;
import com.sineverything.news.bean.order.OrderDetailsResponse;
import com.sineverything.news.bean.order.OrderListResponse;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.order.activity.OrderDetailsActivity;
import com.sineverything.news.ui.order.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class OrderListFragment extends BaseFragment {


    @Bind(R.id.rec_order_list)
    RecyclerView recOrderList;
    @Bind(R.id.xr_freshview)
    XRefreshView xrFreshview;
    private OrderListAdapter orderListAdapter;
    private List<Order> dataList;
    private String status;


    public static Fragment getInstance(String status) {
        OrderListFragment fragment = new OrderListFragment();
        fragment.status = status;
        return fragment;
    }

    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_order_list;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dataList = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            dataList.add(new Order());
        }
        orderListAdapter = new OrderListAdapter(dataList);
        recOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        recOrderList.setAdapter(orderListAdapter);
        orderListAdapter.setItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                loadOrderDetals(dataList.get(postion).getId());
            }
        });
        xrFreshview.setPullRefreshEnable(true);
        xrFreshview.setPullLoadEnable(true);
        xrFreshview.setXRefreshViewListener(new XRefreshView.SimpleXRefreshListener() {
            @Override
            public void onRefresh() {
                super.onRefresh();
                Log.d("zajiaxiaozi", "onRefresh");

            }

            @Override
            public void onRefresh(boolean isPullDown) {
                super.onRefresh(isPullDown);
                loadOrder(LoadMode.NOMAL);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                loadOrder(LoadMode.PULL_REFRSH);
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
    }

    private int page = 0;

    /**
     * 文件的
     */
    private void loadOrder(final LoadMode loadMode) {

        if (loadMode == LoadMode.NOMAL) {

        }
        if (loadMode != LoadMode.UP_REFRESH) {
            page = 0;
        }
        startProgressDialog();

        OkHttpUtils.post()
                .url(HostConstants.INDEX_HOTS)
                .addParams("pageSize", "30")
                .addParams("pageIndex", page + "")
                .addParams("status", status)
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
                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        xrFreshview.stopRefresh();
                        xrFreshview.stopLoadMore();
                    }
                });

            }

            @Override
            public void onResponse(String response) {

                OrderListResponse orderResponse = GsonUtil.changeGsonToBean(response, OrderListResponse.class);
                if (orderResponse != null) {
                    if (isOkCode(orderResponse.getCode(), orderResponse.getMessage())) {
                        // 成功
                        if (loadMode != LoadMode.UP_REFRESH) {
                            dataList.clear();
                        }
                        page++;
                        if (orderResponse.getResult() != null) {
                            dataList.addAll(orderResponse.getResult());
                            orderListAdapter.notifyDataSetChanged();
                        } else {
//                            onEmpty();
                        }

                        if (dataList.size() > 16) {
//                            xr_freshview.setPullLoadEnable(true);
                        } else {
//                            xr_freshview.setPullLoadEnable(false);
                        }
                    }


                }

            }
        });
    }


    /**
     *下载详情
     */
    private void loadOrderDetals(final String orderId) {
        startProgressDialog();
        OkHttpUtils.post()
                .url(HostConstants.INDEX_HOTS)
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
                OrderDetailsResponse menuResponse = GsonUtil.changeGsonToBean(response, OrderDetailsResponse.class);
                if (menuResponse != null) {
                    if (isOkCode(menuResponse.getCode(), menuResponse.getMessage())) {
                        // 成功
                        OrderDetails result = menuResponse.getResult();
                        OrderDetailsActivity.startAction(getContext(), result);
                    }
                }
            }
        });
    }
}

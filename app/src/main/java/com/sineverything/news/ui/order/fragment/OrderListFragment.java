package com.sineverything.news.ui.order.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
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
import com.sineverything.news.bean.order.Order;
import com.sineverything.news.bean.order.OrderDetails;
import com.sineverything.news.bean.order.OrderDetailsResponse;
import com.sineverything.news.bean.order.OrderListResponse;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.comm.widget.MultiStateView;
import com.sineverything.news.ui.order.activity.OrderDetailsActivity;
import com.sineverything.news.ui.order.adapter.OrderListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
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
    @Bind(R.id.multiStateView)
    MultiStateView multiStateView;
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
        orderListAdapter = new OrderListAdapter(dataList);
        recOrderList.setLayoutManager(new LinearLayoutManager(getContext()));
        recOrderList.setAdapter(orderListAdapter);
        orderListAdapter.setItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {


                loadOrderDetals(dataList.get(postion).getId());

//                Order order = dataList.get(postion);
//
//                PaymentActivity.startAction(getActivity(),order.getId(),order.getTotalPrice());
            }
        });
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
        loadOrder(LoadMode.NOMAL);

    }

    private int page = 1;

    /**
     * 文件的
     */
    private void loadOrder(final LoadMode loadMode) {


        if (loadMode != LoadMode.UP_REFRESH) {
            page = 1;
        }


        OkHttpUtils.post()
                .url(HostConstants.ORDER_LIST)
                .addParams("pageSize", "30")
                .addParams("pageIndex", page + "")
                .addParams("status", status)
                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                multiStateView.setViewState(MultiStateView.VIEW_STATE_ERROR);

            }

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

                if (dataList.size() == 0) {
                    multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);

                } else {
                    multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                }

            }
        });
    }


    /**
     * 下载详情
     */
    private void loadOrderDetals(final String orderId) {
        startProgressDialog();
        OkHttpUtils.post()
                .url(HostConstants.ORDER_DETAIL)
                .addParams("orderId", orderId)
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
                        result.setOrderId(orderId);
                        OrderDetailsActivity.startAction(getContext(), result);
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

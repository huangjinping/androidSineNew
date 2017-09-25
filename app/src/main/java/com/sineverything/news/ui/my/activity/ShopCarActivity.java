package com.sineverything.news.ui.my.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.Response;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.bean.my.Goodscart;
import com.sineverything.news.bean.my.GoodscartResponse;
import com.sineverything.news.bean.my.GoodscartResult;
import com.sineverything.news.bean.my.ShopCarSubmit;
import com.sineverything.news.bean.order.SubmitOrder;
import com.sineverything.news.bean.order.SubmitOrderResponse;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.comm.widget.MultiStateView;
import com.sineverything.news.ui.my.adapter.ShopCarAdapter;
import com.sineverything.news.ui.order.activity.ConfirmOrderActivity;
import com.sineverything.news.ui.order.activity.PaymentActivity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/9.
 * email : huangjinping@hdfex.com
 */

public class ShopCarActivity extends BaseActivity implements ShopCarAdapter.ShopCartCallBack {


    @Bind(R.id.rec_shop_car)
    RecyclerView recShopCar;
    @Bind(R.id.xr_freshview)
    XRefreshView xrFreshview;
    @Bind(R.id.layout_selected_all)
    LinearLayout layoutSelectedAll;
    @Bind(R.id.txt_totalPrice)
    TextView txtTotalPrice;
    @Bind(R.id.txt_totalPrice_red)
    TextView txtTotalPriceRed;
    @Bind(R.id.img_select_all)
    ImageView imgSelectAll;
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.txt_shapcart_delete)
    TextView txtShapcartDelete;
    @Bind(R.id.layout_complate)
    LinearLayout layoutComplate;
    @Bind(R.id.multiStateView)
    MultiStateView multiStateView;
    private ShopCarAdapter mAdapter;
    private List<Goodscart> dataList;
    private User user;
    private final String SELECTED_ALL = "SELECTED_ALL";
    private final String SELECTED_UNALL = "SELECTED_UNALL";
    private final String EDIT = "EDIT";
    private final String COMPLATE = "COMPLATE";
    private String status = COMPLATE;
    private String totalPrice;

    @Override
    public int getLayoutId() {
        return R.layout.activity_shopcar;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        dataList = new ArrayList<>();
        mAdapter = new ShopCarAdapter(dataList);
        user = UserManager.getUser(this);

        recShopCar.setLayoutManager(new LinearLayoutManager(this));
        recShopCar.setAdapter(mAdapter);
        mAdapter.setCartCallBack(this);
        mAdapter.notifyDataSetChanged();
        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        navBar.setOnRightTextListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (COMPLATE.equals(status)) {
                    status = EDIT;
                    updateStatusView();
                } else {
                    status = COMPLATE;
                    updateStatusView();
                }

            }
        });
        navBar.setTitleText("购物车");
        navBar.setRightTitleVisibility(true);
        navBar.setRightTitle("编辑");
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
        layoutSelectedAll.setTag(SELECTED_ALL);
        updateStatusView();
    }


    @Override
    protected void onResume() {
        super.onResume();

        loadData(LoadMode.NOMAL);
    }

    /**
     * 提交
     *
     * @param context
     */
    public static void startAction(Context context) {
        Intent intent = new Intent(context, ShopCarActivity.class);
        context.startActivity(intent);
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
                .url(HostConstants.GOODS_CART_LIST)
                .addParams("userId", user.getId())
                .addParams("token", user.getToken())
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
                        runOnUiThread(new Runnable() {
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
                            GoodscartResponse newsItemResponse = GsonUtil.changeGsonToBean(response, GoodscartResponse.class);
                            if (isOkCode(newsItemResponse.getCode(), newsItemResponse.getMessage())) {
                                GoodscartResult result = newsItemResponse.getResult();
                                if (loadMode != LoadMode.UP_REFRESH) {
                                    dataList.clear();
                                    page++;
                                }
                                dataList.addAll(result.getCartList());
                                for (int i = 0; i < dataList.size(); i++) {
                                    dataList.get(i).setSelected(true);
                                }
                                mAdapter.notifyDataSetChanged();
                            }
                            updateBottom();

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


    @OnClick(R.id.layout_selected_all)
    public void selectedAll() {

        if (SELECTED_ALL.equals(layoutSelectedAll.getTag().toString())) {
            layoutSelectedAll.setTag(SELECTED_UNALL);
            setSeleted(false);
        } else {
            layoutSelectedAll.setTag(SELECTED_ALL);
            setSeleted(true);
        }
        updateBottom();
        mAdapter.notifyDataSetChanged();
    }

    /**
     * 编辑问题
     */
    private void updateStatusView() {
        if (COMPLATE.equals(status)) {

            layoutComplate.setVisibility(View.VISIBLE);
            txtShapcartDelete.setVisibility(View.GONE);
            navBar.setRightTitle("编辑");
        } else {

            layoutComplate.setVisibility(View.GONE);
            txtShapcartDelete.setVisibility(View.VISIBLE);
            navBar.setRightTitle("完成");
        }
    }


    /**
     * 全选
     */
    private void setSeleted(boolean selected) {
        for (int i = 0; i < dataList.size(); i++) {
            dataList.get(i).setSelected(selected);
        }
    }

    /**
     *
     */
    private void updateBottom() {
        BigDecimal bigdecimal = new BigDecimal("0.00");
        imgSelectAll.setImageResource(R.mipmap.ic_selected);
        if (dataList.size()==0){
            imgSelectAll.setImageResource(R.mipmap.ic_unselected);
        }
        for (int i = 0; i < dataList.size(); i++) {
            Goodscart goodscart = dataList.get(i);
            if (goodscart.isSelected()) {
                BigDecimal b = new BigDecimal(goodscart.getPrice());
                b = b.multiply(new BigDecimal(goodscart.getCount())).setScale(2, BigDecimal.ROUND_HALF_UP);
                bigdecimal = add(bigdecimal.toString(), b.toString());
            } else {
                imgSelectAll.setImageResource(R.mipmap.ic_unselected);
            }
        }

        totalPrice = bigdecimal.toString();
        txtTotalPrice.setText("S$" + bigdecimal.toString());
        txtTotalPriceRed.setText("S$结算（" + bigdecimal.toString() + "）");
    }


    public BigDecimal add(String value1, String value2) {
        BigDecimal b1 = new BigDecimal(value1);
        BigDecimal b2 = new BigDecimal(value2);
        return b1.add(b2);

    }

    @Override
    public void reduce(int position, int count) {
        goodscartEdit(position, count);
    }

    @Override
    public void add(int position, int count) {
        goodscartEdit(position, count);
    }

    @Override
    public void selected(int position, boolean selected) {

        dataList.get(position).setSelected(selected);
        updateBottom();

        mAdapter.notifyDataSetChanged();
    }


    /**
     * 添加购物车
     */
    private void goodscartEdit(final int position, final int count) {
        startProgressDialog();

        OkHttpUtils.post()
                .url(HostConstants.GOODS_CART_EDIT)
                .addParams("userId", user.getId())
                .addParams("token", user.getToken())
                .addParams("count", count + "")
                .addParams("cartId", dataList.get(position).getCartId())

                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {

            }

            @Override
            public void onBefore(Request request) {
                super.onBefore(request);
                startProgressDialog();

            }

            @Override
            public void onAfter() {
                super.onAfter();
                stopProgressDialog();

            }

            @Override
            public void onResponse(String response) {
                Response response1 = GsonUtil.changeGsonToBean(response, Response.class);
                if (response1 != null) {
                    if (isOkCode(response1.getCode(), response1.getMessage())) {
                        dataList.get(position).setCount(count);
                        updateBottom();
                        mAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }

    /**
     * 查询选中的ids列表用，分开
     *
     * @return
     */
    private String getSelectedIds() {
        StringBuffer buffer = new StringBuffer();
        for (Goodscart cart : dataList) {

            if (cart.isSelected()){
                buffer.append(cart.getCartId());
                buffer.append(",");
            }

        }
        if (buffer.length() > 0) {
            buffer.deleteCharAt(buffer.length() - 1);
        }
        return buffer.toString();
    }

    /**
     *
     */
    @OnClick(R.id.txt_shapcart_delete)
    public void onDelete() {
        startProgressDialog();
        OkHttpUtils.post()
                .url(HostConstants.GOODS_CART_DELETE)
                .addParams("userId", user.getId())
                .addParams("token", user.getToken())
                .addParams("cartIds", getSelectedIds())
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
                Response response1 = GsonUtil.changeGsonToBean(response, Response.class);
                if (response1 != null) {
                    if (isOkCode(response1.getCode(), response1.getMessage())) {
                        showLongToast("删除成功");
                        loadData(LoadMode.NOMAL);
                    }
                }
            }
        });
    }


    @OnClick(R.id.txt_totalPrice_red)
    public void toPay() {
        if (TextUtils.isEmpty(getSelectedIds())) {
            showLongToast("选择购买的商品");
            return;
        }
        ShopCarSubmit submit=new ShopCarSubmit();
        submit.setSelectedIds(getSelectedIds());
            List<Goodscart> goodscarts=new ArrayList<>();
        for (Goodscart cart : dataList) {
            if (cart.isSelected()){
                goodscarts.add(cart);
            }
        }
        submit.setGoodscarts(goodscarts);
        submit.setTotalPrice(totalPrice);

        ConfirmOrderActivity.startAction(this,submit);

    }


}

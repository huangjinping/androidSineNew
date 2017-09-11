package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.bean.commodity.GoodsDetails;
import com.sineverything.news.bean.commodity.GoodsDetailsResponse;
import com.sineverything.news.bean.commodity.GoodsInfos;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.order.adapter.ConfirmOrderAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/7.
 * email : huangjinping@hdfex.com
 */

public class ConfirmOrderActivity extends BaseActivity {

    @Bind(R.id.rec_confirm_order)
    RecyclerView recConfirmOrder;
    @Bind(R.id.txt_submit)
    TextView txtSubmit;
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    private List<Goods> dataList;
    private ConfirmOrderAdapter adapter;
    private GoodsDetails goodDetails;
    private User user;
    private Goods goods;

    @Override
    public int getLayoutId() {
        return R.layout.activity_confirmorder;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        navBar.setTitleText("确认订单");
        Intent intent = getIntent();
        dataList = new ArrayList<>();
        user = UserManager.getUser(this);
        goodDetails = (GoodsDetails) intent.getSerializableExtra(GoodsDetails.class.getSimpleName());
        goods = (Goods) intent.getSerializableExtra(Goods.class.getSimpleName());
        dataList.add(goods);


        adapter = new ConfirmOrderAdapter(dataList);
        recConfirmOrder.setLayoutManager(new LinearLayoutManager(this));
        recConfirmOrder.setAdapter(adapter);
    }

    public static void startAction(Context context, GoodsDetails goodDetails, Goods goods) {

        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra(GoodsDetails.class.getSimpleName(), goodDetails);
        intent.putExtra(Goods.class.getSimpleName(), goods);
        context.startActivity(intent);
    }

    @OnClick(R.id.txt_submit)
    public void submit() {
        startProgressDialog();

        GoodsInfos info = new GoodsInfos();
        info.setGoodsCount("1");
        info.setGoodsAllPrice(goodDetails.getStorePrice());
        info.setGoodsId(goodDetails.getGoodsId());
        info.setGoodsAllPrice(goodDetails.getStorePrice());
        List<GoodsInfos> goodsInfos = new ArrayList<>();
        goodsInfos.add(info);


        OkHttpUtils.post()
                .url(HostConstants.ORDER_SUBMIT)
                .addParams("goodsInfos", GsonUtil.createGsonString(goodsInfos))
                .addParams("userId", user.getId())
                .addParams("token", user.getToken())
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
                GoodsDetailsResponse detailsResponse = GsonUtil.changeGsonToBean(response, GoodsDetailsResponse.class);
                if (detailsResponse != null) {
                    if (isOkCode(detailsResponse.getCode(), detailsResponse.getMessage())) {
                        GoodsDetails result = detailsResponse.getResult();
                        OrderListActivity.startAction(mContext);
                    }
                }
            }
        });
    }


}

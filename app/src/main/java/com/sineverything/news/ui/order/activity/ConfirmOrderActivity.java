package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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
import com.sineverything.news.bean.commodity.GoodsInfos;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.bean.order.SubmitOrder;
import com.sineverything.news.bean.order.SubmitOrderResponse;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.main.activity.MainActivity;
import com.sineverything.news.ui.my.activity.LoginActivity;
import com.sineverything.news.ui.order.adapter.ConfirmOrderAdapter;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
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
    @Bind(R.id.txt_totalPrice)
    TextView txtTotalPrice;
    private List<Goods> dataList;
    private ConfirmOrderAdapter adapter;
    private GoodsDetails goodDetails;
    private User user;
    private Goods goods;
    private int goodsCount;
    private BigDecimal totalPrice;

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
        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMain();
                finish();

            }
        });
        Intent intent = getIntent();
        dataList = new ArrayList<>();
        goodDetails = (GoodsDetails) intent.getSerializableExtra(GoodsDetails.class.getSimpleName());
        goods = (Goods) intent.getSerializableExtra(Goods.class.getSimpleName());
        goodsCount = intent.getIntExtra("GoodsCount", 1);
        goods.setConunt(goodsCount + "");
        dataList.add(goods);
        adapter = new ConfirmOrderAdapter(dataList);
        recConfirmOrder.setLayoutManager(new LinearLayoutManager(this));
        recConfirmOrder.setAdapter(adapter);
        BigDecimal b = new BigDecimal(goods.getGoodsPrice());
        totalPrice = b.multiply(new BigDecimal(goodsCount)).setScale(2, BigDecimal.ROUND_HALF_UP);
        txtTotalPrice.setText("S$" + totalPrice.toString());

    }

    public static void startAction(Context context, GoodsDetails goodDetails, Goods goods, int goodsCount) {
        Intent intent = new Intent(context, ConfirmOrderActivity.class);
        intent.putExtra(GoodsDetails.class.getSimpleName(), goodDetails);
        intent.putExtra(Goods.class.getSimpleName(), goods);
        intent.putExtra("GoodsCount", goodsCount);
        context.startActivity(intent);
    }


    @Override
    protected void onResume() {
        super.onResume();
        user = UserManager.getUser(this);
    }

    @OnClick(R.id.txt_submit)
    public void submit() {


        if (!UserManager.isLogin(this)) {
            LoginActivity.startAction(this);
            return;
        }


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
                .addParams("totalPrice", totalPrice.toString())
                .addParams("payType", "online")
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
                SubmitOrderResponse detailsResponse = GsonUtil.changeGsonToBean(response, SubmitOrderResponse.class);
                if (detailsResponse != null) {
                    if (isOkCode(detailsResponse.getCode(), detailsResponse.getMessage())) {
                        SubmitOrder result = detailsResponse.getResult();
                        PaymentActivity.startAction(mContext, result.getOrderId(), totalPrice.toString());
                    }
                }
            }
        });
    }


    private void toMain() {
        Intent intent = new Intent(mContext, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}

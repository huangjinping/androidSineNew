package com.sineverything.news.ui.order.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.alipay.sdk.app.PayTask;
import com.jaydenxiao.common.alipay.PayResult1;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.bean.order.PayInfoResponse;
import com.sineverything.news.comm.UserManager;

import java.util.Map;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/9/6.
 * email : huangjinping@hdfex.com
 */

public class PaymentActivity extends BaseActivity {
    @Bind(R.id.txt_price)
    TextView txtPrice;
    @Bind(R.id.txt_weixin)
    TextView txtWeixin;
    @Bind(R.id.btn_submit)
    Button btnSubmit;
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;

    private String orderId;
    private String totalAmount;
    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;
    private User user;


    public static void startAction(Context context, String orderId, String totalAmount) {
        Intent intent = new Intent(context, PaymentActivity.class);
        intent.putExtra("orderId", orderId);
        intent.putExtra("totalAmount", totalAmount);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_payment;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        user = UserManager.getUser(this);
        Intent intent = getIntent();
        orderId = intent.getStringExtra("orderId");
        totalAmount = intent.getStringExtra("totalAmount");
        navBar.setTitleText("支付");
        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        txtPrice.setText("$" + totalAmount);
    }


    @OnClick(R.id.btn_submit)
    public void onSubmit() {
        OkHttpUtils.post()
                .url(HostConstants.PAYMENT_ALIPAY)
                .addParams("userId", user.getId())
                .addParams("orderId", orderId)
                .addParams("totalAmount", totalAmount)

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
                PayInfoResponse detailsResponse = GsonUtil.changeGsonToBean(response, PayInfoResponse.class);
                if (detailsResponse != null) {
                    if (isOkCode(detailsResponse.getCode(), detailsResponse.getMessage())) {
                        payment(detailsResponse.getResult().getOrderInfo());
                    }
                }
            }
        });
    }


    private void payment(final String orderInfo) {

        Log.d("okhttp",orderInfo);
        Runnable payRunnable = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(PaymentActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);
                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult1 payResult = new PayResult1((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();

                    Log.d("okhttp", resultInfo + "=====" + resultStatus);
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        showLongToast("支付成功");
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        showLongToast("支付失败");

                    }
                    break;
                }

            }
        }

        ;
    };



}

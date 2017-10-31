package com.sineverything.news.ui.order.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.Response;
import com.sineverything.news.bean.main.User;
import com.sineverything.news.bean.order.GoodsInfo;
import com.sineverything.news.bean.order.OrderDetails;
import com.sineverything.news.comm.UserManager;
import com.sineverything.news.ui.my.activity.LoginActivity;
import com.sineverything.news.ui.order.adapter.OrderDetailsAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/15.
 * email : huangjinping@hdfex.com
 */

public class OrderDetailsActivity extends BaseActivity {


    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.rec_order_details)
    RecyclerView recOrderDetails;
    @Bind(R.id.txt_return)
    TextView txtReturn;
    @Bind(R.id.txt_payment)
    TextView txtPayment;
    @Bind(R.id.edt_comment_content)
    EditText edtCommentContent;
    @Bind(R.id.txt_comment_submit)
    TextView txtCommentSubmit;
    @Bind(R.id.layout_comments)
    LinearLayout layoutComments;
    private OrderDetailsAdapter detailsAdapter;
    private List<GoodsInfo> dataList;
    private OrderDetails details;
    private User user;


    public static void startAction(Context context, OrderDetails result) {
        Intent intent = new Intent(context, OrderDetailsActivity.class);
        intent.putExtra(OrderDetails.class.getSimpleName(), result);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutId() {

        return R.layout.act_orderdetails;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        navBar.setTitleText("订单详情");
        user = UserManager.getUser(this);

        Intent intent = getIntent();
        dataList = new ArrayList<>();
        details = (OrderDetails) intent.getSerializableExtra(OrderDetails.class.getSimpleName());
        List<GoodsInfo> goodsInfo = details.getGoodsInfo();
        dataList.addAll(goodsInfo);
        detailsAdapter = new OrderDetailsAdapter(dataList, details);
        recOrderDetails.setLayoutManager(new LinearLayoutManager(this));
        recOrderDetails.setAdapter(detailsAdapter);
//        status   10 待付款  20 代发货 30 待收货 40 待评价 50 已完成 0 已取消

        if (!TextUtils.isEmpty(details.getOrderStatus())) {
            String status = details.getOrderStatus();
            if ("10".equals(status)) {
                txtPayment.setVisibility(View.VISIBLE);

            } else if ("30".equals(status)) {
//                txtReturn.setVisibility(View.VISIBLE);
            } else if ("40".equals(status)) {
            }
        }
//        layoutComments.setVisibility(View.VISIBLE);

        navBar.setOnBackListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @OnClick(R.id.txt_payment)
    public void onPay() {
        PaymentActivity.startAction(this, details.getOrderId(), details.getTotalPrice());
    }


    /**
     * 提交
     */
    @OnClick(R.id.txt_comment_submit)
    public void submitCommemt() {
        if (!UserManager.isLogin(this)) {
            LoginActivity.startAction(this);
            return;
        }
        String commentContent = edtCommentContent.getText().toString().trim();
        if (!TextUtils.isEmpty(commentContent)) {
            addComment(details.getOrderId(), commentContent);
        }
    }

    /**
     * 添加评论
     */
    private void addComment(String cmsId, String content) {
        if (!UserManager.isLogin(this)) {
            LoginActivity.startAction(this);
            return;
        }
        OkHttpUtils.post()
                .tag(this)
                .addParams("cmsId", cmsId)
                .addParams("commentId", "")
                .addParams("content", content)
                .addParams("userId", user.getId())
                .addParams("token", user.getToken())
                .url(HostConstants.COMMENTS_ADD)

                .build()
                .execute(new StringCallback() {
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
                    public void onError(Call call, Exception e) {
                    }

                    @Override
                    public void onResponse(String response) {
                        try {

                            Response response1 = GsonUtil.changeGsonToBean(response, Response.class);
                            if (isOkCode(response1.getCode(), response1.getMessage())) {
                                showLongToast("评价成功");
                                finish();
                            }
                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                    }
                });
    }
}

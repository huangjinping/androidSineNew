package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.andview.refreshview.XRefreshView;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.commonwidget.NormalTitleBar;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.main.Comments;
import com.sineverything.news.bean.main.CommentsResponse;
import com.sineverything.news.comm.widget.MultiStateView;
import com.sineverything.news.ui.commodity.adapter.CommodityCommentAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/9/27.
 * email : huangjinping@hdfex.com
 */

public class CommodityCommentsActivity extends BaseActivity {
    @Bind(R.id.nav_bar)
    NormalTitleBar navBar;
    @Bind(R.id.rec_order_list)
    RecyclerView recOrderList;
    @Bind(R.id.xr_freshview)
    XRefreshView xrFreshview;
    @Bind(R.id.multiStateView)
    MultiStateView multiStateView;
    private List<Comments> commentsList;
    private String id;
    private CommodityCommentAdapter commentAdapter;

    @Override
    public int getLayoutId() {
        return R.layout.activity_commoditycomments;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        id = intent.getStringExtra("id");
        commentsList = new ArrayList<>();
        navBar.setTitleText("商品评论");
        navBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        commentAdapter = new CommodityCommentAdapter(commentsList);
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
                loadComments(LoadMode.NOMAL);
            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                loadComments(LoadMode.PULL_REFRSH);
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
        loadComments(LoadMode.NOMAL);

    }

    /**
     * @param cmsId
     */


    private int page = 1;

    private void loadComments(final LoadMode loadMode) {
        if (loadMode != LoadMode.UP_REFRESH) {
            page = 1;
        }
        if (loadMode == LoadMode.NOMAL) {
            startProgressDialog();
        }
        OkHttpUtils.post()
                .tag(this)
                .addParams("cmsId", id)

                .url(HostConstants.COMMENTS)
                .addParams("pageIndex", page + "")
                .addParams("pageSize", "30")
                .build()
                .execute(new StringCallback() {
                    @Override
                    public void onBefore(Request request) {
                        super.onBefore(request);
                    }

                    @Override
                    public void onAfter() {
                        super.onAfter();
                        stopProgressDialog();
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
                    }

                    @Override
                    public void onResponse(String response) {
                        try {
                            CommentsResponse newsItemResponse = GsonUtil.changeGsonToBean(response, CommentsResponse.class);
                            if (isOkCode(newsItemResponse.getCode(), newsItemResponse.getMessage())) {
                                List<Comments> result = newsItemResponse.getResult();
                                if (loadMode != LoadMode.UP_REFRESH) {
                                    commentsList.clear();
                                    page++;
                                }
                                commentsList.addAll(result);
                                commentAdapter.notifyDataSetChanged();
                            }

                        } catch (Exception e) {
                            e.printStackTrace();

                        }

                        if (commentsList.size() == 0) {
                            multiStateView.setViewState(MultiStateView.VIEW_STATE_EMPTY);

                        } else {
                            multiStateView.setViewState(MultiStateView.VIEW_STATE_CONTENT);
                        }

                    }
                });
    }

    /**
     * @param context
     * @param id
     */
    public static void startAction(Context context, String id) {
        Intent intent = new Intent(context, CommodityCommentsActivity.class);
        intent.putExtra("id", id);
        context.startActivity(intent);
    }
}

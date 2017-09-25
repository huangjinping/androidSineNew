package com.sineverything.news.ui.commodity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.XRefreshView;
import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.jaydenxiao.common.base.BaseActivity;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.bean.commodity.GoodsResponse;
import com.sineverything.news.comm.TabEntity;
import com.sineverything.news.ui.commodity.adapter.CommodityListAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/5.
 * email : huangjinping@hdfex.com
 */

public class CommodityActivity extends BaseActivity {

    @Bind(R.id.ic_left_icon)
    ImageView icLeftIcon;
    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.txt_action)
    TextView txtAction;
    @Bind(R.id.layout_filter)
    CommonTabLayout layoutFilter;
    @Bind(R.id.rec_commodity_list)
    RecyclerView recCommodityList;
    @Bind(R.id.xr_freshview)
    XRefreshView xrFreshview;
    @Bind(R.id.cb_place)
    CheckBox cb1;
    @Bind(R.id.ll_place_tab)
    LinearLayout llPlaceTab;
    @Bind(R.id.cb_type)
    CheckBox cb2;
    @Bind(R.id.ll_type)
    LinearLayout llType;
    @Bind(R.id.cb_time)
    CheckBox cb3;
    @Bind(R.id.ll_time)
    LinearLayout llTime;
    @Bind(R.id.ll_stay_visit_selsect)
    LinearLayout llStayVisitSelsect;
    private String[] mTitles = {"综合", "销量", "价格"};
    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();
    private List<Goods> dataList;
    private CommodityListAdapter adapter;


    private int[] mIconSelectIds = {
            R.mipmap.edit_search_down, R.mipmap.edit_search_down,
            R.mipmap.edit_search_down, R.mipmap.edit_search_down};
    private int[] mIconUnselectIds = {
            R.mipmap.edit_search_down, R.mipmap.edit_search_down,
            R.mipmap.edit_search_down, R.mipmap.edit_search_down};
    private String classId = "";
    private String keywords = "";


    private String orderBy;
    private  String orderType;


    @Override
    public int getLayoutId() {
        return R.layout.activity_commodity;
    }

    @Override
    public void initPresenter() {


    }

    @Override
    public void initView() {
        Intent intent = getIntent();
        classId = intent.getStringExtra("classId");
        keywords = intent.getStringExtra("keywords");
        if (!TextUtils.isEmpty(keywords)){
            edtSearch.setText(keywords);
            edtSearch.setSelection(keywords.length());
        }

        String[] title = {"综合", "销量", "价格"};
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnselectIds[i]));
        }
        layoutFilter.setTabData(mTabEntities);
        dataList = new ArrayList<>();
        adapter = new CommodityListAdapter(dataList);
        recCommodityList.setLayoutManager(new LinearLayoutManager(this));
        recCommodityList.setAdapter(adapter);

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
                loadGoods(LoadMode.NOMAL);


            }

            @Override
            public void onLoadMore(boolean isSilence) {
                super.onLoadMore(isSilence);
                loadGoods(LoadMode.PULL_REFRSH);

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
        loadGoods(LoadMode.NOMAL);
        initButton();

    }

    @OnClick(R.id.txt_action)
    public void search() {
        SearchCommodityActivity.startAction(this);
    }

    public static void startActionWithId(Context context, String classId) {
        Intent intent = new Intent(context, CommodityActivity.class);
        intent.putExtra("classId", classId);
        context.startActivity(intent);

    }

    public static void startActionWithKeywords(Context context, String keywords) {
        Intent intent = new Intent(context, CommodityActivity.class);
        intent.putExtra("keywords", keywords);
        context.startActivity(intent);
    }


    private int page = 1;

    /**
     * 文件的
     */
    private void loadGoods(final LoadMode loadMode) {

        if (loadMode == LoadMode.NOMAL) {
            startProgressDialog();
        }
        if (loadMode != LoadMode.UP_REFRESH) {
            page = 1;
        }

        OkHttpUtils.post()
                .url(HostConstants.GOODS_LIST)
                .addParams("pageSize", "15")
                .addParams("pageIndex", page + "")
                .addParams("keywords", keywords)
                .addParams("classId", classId)
                .addParams("orderBy", orderBy)
                .addParams("orderType", orderType)

                .build().execute(new StringCallback() {
            @Override
            public void onError(Call call, Exception e) {
                stopProgressDialog();
            }

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
            public void onResponse(String response) {
                stopProgressDialog();
                GoodsResponse goodsResponse = GsonUtil.changeGsonToBean(response, GoodsResponse.class);
                if (goodsResponse != null) {
                    if (isOkCode(goodsResponse.getCode(), goodsResponse.getMessage())) {
                        // 成功
                        if (loadMode != LoadMode.UP_REFRESH) {
                            dataList.clear();
                        }
                        page++;
                        if (goodsResponse.getResult() != null) {
                            dataList.addAll(goodsResponse.getResult());
                            adapter.notifyDataSetChanged();
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

    @OnClick(R.id.ic_left_icon)
    public void onBack() {
        finish();
    }



    private void initButton(){
        // cb1操作
        cb1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 将其他的cb设置为未选中,将自己设置为选中

                Log.d("hjp","cb1");
                cb2.setChecked(false);
                cb3.setChecked(false);
                orderBy="wellEvaluate";
                if (b){
                    orderType="asc";
                }else {
                    orderType="desc";
                }

                loadGoods(LoadMode.NOMAL);

            }
        });

        // cb2操作
        cb2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 将其他的cb设置为未选中,将自己设置为选中
                Log.d("hjp","cb2");

                cb1.setChecked(false);
                cb3.setChecked(false);
                orderBy="saleNum";
                if (b){
                    orderType="asc";
                }else {
                    orderType="desc";
                }

                loadGoods(LoadMode.NOMAL);

            }
        });
        // cb3操作
        cb3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                // 将其他的cb设置为未选中,将自己设置为选中
                Log.d("hjp","cb3");

                cb1.setChecked(false);
                cb2.setChecked(false);
                orderBy="price";
                if (b){
                    orderType="asc";
                }else {
                    orderType="desc";
                }

                loadGoods(LoadMode.NOMAL);

            }
        });
    }
}

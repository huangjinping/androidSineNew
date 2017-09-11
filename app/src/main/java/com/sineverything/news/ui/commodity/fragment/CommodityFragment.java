package com.sineverything.news.ui.commodity.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.andview.refreshview.XRefreshView;
import com.jaydenxiao.common.base.BaseFragment;
import com.jaydenxiao.common.okhttp.LoadMode;
import com.jaydenxiao.common.okhttp.OkHttpUtils;
import com.jaydenxiao.common.okhttp.callback.StringCallback;
import com.jaydenxiao.common.utils.GsonUtil;
import com.sineverything.news.R;
import com.sineverything.news.api.HostConstants;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.bean.commodity.GoodsResponse;
import com.sineverything.news.bean.commodity.MenuItem;
import com.sineverything.news.bean.commodity.MenuResponse;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.commodity.ClassifyActivity;
import com.sineverything.news.ui.commodity.CommodityActivity;
import com.sineverything.news.ui.commodity.CommodityDetailsActivity;
import com.sineverything.news.ui.commodity.SearchCommodityActivity;
import com.sineverything.news.ui.commodity.adapter.CommodityAdapter;
import com.sineverything.news.ui.my.activity.SelectAreaActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import okhttp3.Call;
import okhttp3.Request;

/**
 * author Created by harrishuang on 2017/8/5.
 * email : huangjinping@hdfex.com
 */

public class CommodityFragment extends BaseFragment {


    @Bind(R.id.ic_left_icon)
    ImageView icLeftIcon;
    @Bind(R.id.edt_search)
    EditText edtSearch;
    @Bind(R.id.layout_search)
    LinearLayout layoutSearch;
    @Bind(R.id.rec_commodity)
    RecyclerView recCommodity;
    @Bind(R.id.xr_freshview)
    XRefreshView xrFreshview;
    private List<Goods> dataList;
    private CommodityAdapter adapter;

    private List<MenuItem> menuList;


    public static BaseFragment getInstance() {
        CommodityFragment fragment = new CommodityFragment();
        return fragment;
    }


    @Override
    protected int getLayoutResource() {
        return R.layout.fragment_commodity;
    }

    @Override
    public void initPresenter() {

    }

    @Override
    protected void initView() {
        dataList = new ArrayList<>();
        menuList = new ArrayList<>();


        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position == 0 ? 2 : 1;
            }
        });
        recCommodity.setLayoutManager(layoutManager);
        adapter = new CommodityAdapter(menuList, dataList);
        adapter.setItemHeaderClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if (postion == 7) {
                    ClassifyActivity.startAction(getActivity());
                } else {
                    MenuItem item = menuList.get(postion);
                    CommodityActivity.startActionWithId(getActivity(),item.getId());
                }
            }
        });

        adapter.setItemClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                Goods goods = dataList.get(postion);
                CommodityDetailsActivity.startAction(getActivity(), goods);
            }
        });
        recCommodity.setAdapter(adapter);


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

                loadleftCategories();
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
        loadleftCategories();

    }


    @OnClick(R.id.layout_search)
    public void onSearch() {
        SearchCommodityActivity.startAction(getContext());
    }


    /**
     * 一级菜单
     */
    private void loadleftCategories() {

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


            }

            @Override
            public void onResponse(String response) {
                MenuResponse menuResponse = GsonUtil.changeGsonToBean(response, MenuResponse.class);
                if (menuResponse != null) {
                    if (isOkCode(menuResponse.getCode(), menuResponse.getMessage())) {
                        // 成功
                        List<MenuItem> result = menuResponse.getResult();
                        menuList.clear();
                        menuList.addAll(result);
                        MenuItem item = new MenuItem();
                        item.setClassName("分类");
                        item.setDefaultId(R.mipmap.menu_8);
                        menuList.add(item);
                        adapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }


    private int page = 1;

    /**
     * 文件的
     */
    private void loadGoods(final LoadMode loadMode) {

        if (loadMode == LoadMode.NOMAL) {

        }
        if (loadMode != LoadMode.UP_REFRESH) {
            page = 1;
        }
        startProgressDialog();

        OkHttpUtils.post()
                .url(HostConstants.RECOMMDEND_GOODS)
                .addParams("pageSize", "30")
                .addParams("pageIndex", page + "")
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


}

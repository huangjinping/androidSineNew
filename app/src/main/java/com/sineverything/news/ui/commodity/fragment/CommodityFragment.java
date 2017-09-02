package com.sineverything.news.ui.commodity.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.jaydenxiao.common.base.BaseFragment;
import com.sineverything.news.R;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.commodity.ClassifyActivity;
import com.sineverything.news.ui.commodity.CommodityActivity;
import com.sineverything.news.ui.commodity.adapter.CommodityAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;

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
    private List<String> dataList;
    private CommodityAdapter adapter;


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

        for (int i = 0; i < 100; i++) {
            dataList.add("");
        }
        GridLayoutManager layoutManager = new GridLayoutManager(getActivity(), 2);
        layoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int position) {

                return position == 0 ? 2 : 1;
            }
        });
        recCommodity.setLayoutManager(layoutManager);
        adapter = new CommodityAdapter(dataList);
        adapter.setItemHeaderClickListener(new MyItemClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
                if (postion==7){
                    ClassifyActivity.startAction(getActivity());
                }
            }
        });
        recCommodity.setAdapter(adapter);
    }


    @OnClick(R.id.layout_search)
    public void onSearch() {
        CommodityActivity.startAction(getActivity());
    }


}

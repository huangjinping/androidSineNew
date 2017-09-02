package com.sineverything.news.ui.commodity.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Classify;
import com.sineverything.news.comm.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/8/5.
 * email : huangjinping@hdfex.com
 */

public class CommodityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ViewHolder viewHolder;
    private List<String> dataList;

    private static final int HEADER = 1;
    private static final int ITEM = 2;
    final List<Classify> mClassify = new ArrayList<>();

    private MyItemClickListener itemHeaderClickListener;

    public MyItemClickListener getItemHeaderClickListener() {
        return itemHeaderClickListener;
    }

    public void setItemHeaderClickListener(MyItemClickListener itemHeaderClickListener) {
        this.itemHeaderClickListener = itemHeaderClickListener;
    }

    public CommodityAdapter(List<String> dataList) {
        this.dataList = dataList;
        mClassify.add(new Classify("超市", R.mipmap.menu_1));
        mClassify.add(new Classify("化妆品", R.mipmap.menu_2));
        mClassify.add(new Classify("服装", R.mipmap.menu_3));
        mClassify.add(new Classify("手机数码", R.mipmap.menu_4));
        mClassify.add(new Classify("家居家纺", R.mipmap.menu_5));
        mClassify.add(new Classify("生鲜水果", R.mipmap.menu_6));
        mClassify.add(new Classify("充值", R.mipmap.menu_7));
        mClassify.add(new Classify("分类", R.mipmap.menu_8));

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == HEADER) {
            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_commodity_header, parent, false);
            return new HeaderViewHolder(item);
        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_commodity, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
            Context context = headerViewHolder.rec_commodity_header.getContext();
            RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 4);
            headerViewHolder.rec_commodity_header.setLayoutManager(mLayoutManager);
            HomeTopMenuAdapter mAdapter = new HomeTopMenuAdapter(context, mClassify);
            headerViewHolder.rec_commodity_header.setAdapter(mAdapter);
            if (itemHeaderClickListener != null) {
                mAdapter.setmListener(itemHeaderClickListener);
            }
        } else {


        }
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 1;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_item;
        public TextView txt_commodity_name;
        public TextView txt_price;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_item = (ImageView) rootView.findViewById(R.id.img_item);
            this.txt_commodity_name = (TextView) rootView.findViewById(R.id.txt_commodity_name);
            this.txt_price = (TextView) rootView.findViewById(R.id.txt_price);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public LinearLayout layout_header_view;
        public TextView txt_new_title;
        public RecyclerView rec_commodity_header;

        public HeaderViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.layout_header_view = (LinearLayout) rootView.findViewById(R.id.layout_header_view);
            this.txt_new_title = (TextView) rootView.findViewById(R.id.txt_new_title);
            this.rec_commodity_header = (RecyclerView) rootView.findViewById(R.id.rec_commodity_header);
        }

    }
}

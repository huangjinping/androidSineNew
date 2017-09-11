package com.sineverything.news.ui.commodity.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Classify;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.bean.commodity.MenuItem;
import com.sineverything.news.comm.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/8/5.
 * email : huangjinping@hdfex.com
 */

public class CommodityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ViewHolder viewHolder;
    private List<Goods> dataList;

    private static final int HEADER = 1;
    private static final int ITEM = 2;
    private List<MenuItem> mClassify ;


    private MyItemClickListener itemHeaderClickListener;

    private MyItemClickListener itemClickListener;

    public void setItemClickListener(MyItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MyItemClickListener getItemHeaderClickListener() {
        return itemHeaderClickListener;
    }



    public void setItemHeaderClickListener(MyItemClickListener itemHeaderClickListener) {
        this.itemHeaderClickListener = itemHeaderClickListener;
    }

    public CommodityAdapter(List<MenuItem> menuList, List<Goods> dataList) {
        this.dataList = dataList;
        mClassify=menuList;



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
            ViewHolder viewHolder= (ViewHolder) holder;
            Context context=viewHolder.img_item.getContext();
           final int i = position - 1;
            Goods goods = dataList.get(i);
            ImageLoaderUtils.display(context,viewHolder.img_item,goods.getGoodsMainPhoto());

            if (!TextUtils.isEmpty(goods.getGoodsName())){
                viewHolder.txt_goodsName.setText(goods.getGoodsName());
            }
            if (!TextUtils.isEmpty(goods.getGoodsPrice())){
                viewHolder.txt_goodsPrice.setText("S$"+goods.getGoodsPrice());

            }


            viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (itemClickListener!=null){
                        itemClickListener.onItemClick(v,i);
                    }
                }
            });

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




    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_item;
        public TextView txt_goodsName;
        public TextView txt_goodsPrice;
        public ViewHolder(View rootView) {
            super(rootView);

            this.rootView = rootView;
            this.img_item = (ImageView) rootView.findViewById(R.id.img_item);
            this.txt_goodsName = (TextView) rootView.findViewById(R.id.txt_goodsName);
            this.txt_goodsPrice = (TextView) rootView.findViewById(R.id.txt_goodsPrice);
        }

    }
}

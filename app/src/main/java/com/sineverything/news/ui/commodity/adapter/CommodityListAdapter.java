package com.sineverything.news.ui.commodity.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.ui.commodity.CommodityDetailsActivity;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/5.
 * email : huangjinping@hdfex.com
 */

public class CommodityListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Goods> dataList;

    public CommodityListAdapter(List<Goods> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_commoidy_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        Context context = viewHolder.rootView.getContext();
        final Goods goods = dataList.get(position);
        if (!TextUtils.isEmpty(goods.getGoodsMainPhoto())) {
            ImageLoaderUtils.display(context, viewHolder.img_commodity_icon, goods.getGoodsMainPhoto());
        }
        if (!TextUtils.isEmpty(goods.getGoodsName())) {
            viewHolder.txt_goodsName.setText(goods.getGoodsName());
        }
        if (!TextUtils.isEmpty(goods.getGoodsPrice())) {
            viewHolder.txt_goodsPrice.setText("$s"+goods.getGoodsPrice());
        }
        if (!TextUtils.isEmpty(goods.getGoodsSaleNum())) {
            viewHolder.txt_goodsSaleNum.setText(goods.getGoodsSaleNum() + "销量");
        }

        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CommodityDetailsActivity.startAction(v.getContext(), goods);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_commodity_icon;
        public TextView txt_goodsName;
        public TextView txt_describe;
        public TextView txt_goodsPrice;
        public TextView txt_goodsSaleNum;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_commodity_icon = (ImageView) rootView.findViewById(R.id.img_commodity_icon);
            this.txt_goodsName = (TextView) rootView.findViewById(R.id.txt_goodsName);
            this.txt_describe = (TextView) rootView.findViewById(R.id.txt_describe);
            this.txt_goodsPrice = (TextView) rootView.findViewById(R.id.txt_goodsPrice);
            this.txt_goodsSaleNum = (TextView) rootView.findViewById(R.id.txt_goodsSaleNum);
        }

    }


}

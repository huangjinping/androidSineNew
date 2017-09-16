package com.sineverything.news.ui.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Goods;
import com.sineverything.news.ui.my.activity.AddressActivity;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/7.
 * email : huangjinping@hdfex.com
 */

public class ConfirmOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEADER = 1;
    private final int ITEM = 2;
    private final int FOOTER = 3;
    private List<Goods> dataList;

    public ConfirmOrderAdapter(List<Goods> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        if (HEADER == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_confirmorder_header, parent, false);
            return new ViewHeaderHolder(view);
        } else if (FOOTER == viewType) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_confirmorder_footer, parent, false);
            return new ViewFooterHolder(view);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_confirmorder_item, parent, false);
            return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if (position == 0) {
            ViewHeaderHolder viewHeaderHolder = (ViewHeaderHolder) holder;
            viewHeaderHolder.rootView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AddressActivity.startAction(v.getContext());
                }
            });
        } else if (position == (dataList.size() + 1)) {

        } else {
            int pos = position - 1;
            Goods goods = dataList.get(pos);
            ViewHolder viewholder= (ViewHolder) holder;
            ImageLoaderUtils.display(viewholder.img_icon.getContext(),viewholder.img_icon,goods.getGoodsMainPhoto());
            if (!TextUtils.isEmpty(goods.getGoodsName())){
                viewholder.txt_goodsName.setText(goods.getGoodsName());
            }
            if (!TextUtils.isEmpty(goods.getGoodsPrice())){
                viewholder.txt_storePrice.setText("S$"+goods.getGoodsPrice());
            }
            viewholder.txt_goodsCount.setText("x"+goods.getConunt());
        }
    }
    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEADER;
        } else if (position == (dataList.size() + 1)) {
            return FOOTER;
        } else {
            return ITEM;
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 2;
    }

    public static class ViewHeaderHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_phone;

        public ViewHeaderHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_phone = (TextView) rootView.findViewById(R.id.txt_phone);
        }

    }

    public static class ViewFooterHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_orderId;

        public ViewFooterHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_orderId = (TextView) rootView.findViewById(R.id.txt_orderId);
        }
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_icon;
        public TextView txt_goodsName;
        public TextView txt_param;
        public TextView txt_storePrice;
        public TextView txt_goodsCount;
        public Button txt_return;

        public ViewHolder(View rootView) {
            super(rootView);

            this.rootView = rootView;
            this.img_icon = (ImageView) rootView.findViewById(R.id.img_icon);
            this.txt_goodsName = (TextView) rootView.findViewById(R.id.txt_goodsName);
            this.txt_param = (TextView) rootView.findViewById(R.id.txt_param);
            this.txt_storePrice = (TextView) rootView.findViewById(R.id.txt_storePrice);
            this.txt_goodsCount = (TextView) rootView.findViewById(R.id.txt_goodsCount);
            this.txt_return = (Button) rootView.findViewById(R.id.txt_return);
        }

    }
}

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
import com.sineverything.news.bean.order.GoodsInfo;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class OrderChildAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<GoodsInfo> dataList;

    public OrderChildAdapter(List<GoodsInfo> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_confirmorder_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        GoodsInfo goodsInfo = dataList.get(position);
        ImageLoaderUtils.display(viewHolder.img_icon.getContext(), viewHolder.img_icon, goodsInfo.getGoodsMainphotoPath());

        if (!TextUtils.isEmpty(goodsInfo.getGoodsPrice())) {
            viewHolder.txt_storePrice.setText("S$"+goodsInfo.getGoodsPrice());
        }
        if (!TextUtils.isEmpty(goodsInfo.getGoodsCount())){
            viewHolder.txt_goodsCount.setText("x"+goodsInfo.getGoodsCount());
        }
        if (!TextUtils.isEmpty(goodsInfo.getGoodsName())){
            viewHolder.txt_goodsName.setText(goodsInfo.getGoodsName());
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
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

package com.sineverything.news.ui.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.sineverything.news.R;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/9.
 * email : huangjinping@hdfex.com
 */

public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    private List<String>  dataList;

    public ShopCarAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_shopcar, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_icon;
        public TextView txt_price;
        public EditText edt_account;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_icon = (ImageView) rootView.findViewById(R.id.img_icon);
            this.txt_price = (TextView) rootView.findViewById(R.id.txt_price);
            this.edt_account = (EditText) rootView.findViewById(R.id.edt_account);
        }

    }
}

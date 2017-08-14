package com.sineverything.news.ui.order.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.sineverything.news.R;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/7.
 * email : huangjinping@hdfex.com
 */

public class ConfirmOrderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final int HEADER = 1;
    private final int ITEM = 2;
    private final int FOOTER = 3;
    private List<String> dataList;

    public ConfirmOrderAdapter(List<String> dataList) {
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

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_icon = (ImageView) rootView.findViewById(R.id.img_icon);
        }

    }
}

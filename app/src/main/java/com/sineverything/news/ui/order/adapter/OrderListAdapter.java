package com.sineverything.news.ui.order.adapter;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sineverything.news.R;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class OrderListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> dataList;

    public OrderListAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_parent_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        List<String> data = new ArrayList<>();
        data.add("");
        data.add("");
        OrderChildAdapter childAdapter = new OrderChildAdapter(data);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.rec_order_child.setLayoutManager(new LinearLayoutManager(viewHolder.rec_order_child.getContext()));
        viewHolder.rec_order_child.setAdapter(childAdapter);
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public RecyclerView rec_order_child;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.rec_order_child = (RecyclerView) rootView.findViewById(R.id.rec_order_child);
        }

    }
}

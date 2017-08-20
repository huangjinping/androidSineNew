package com.sineverything.news.ui.commodity.adapter;

import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sineverything.news.R;
import com.sineverything.news.comm.MyItemClickListener;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class MenuAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<String> dataList;
    private MyItemClickListener itemClickListener;

    public void setItemClickListener(MyItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public MenuAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_menu, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
        ViewHolder viewHolder= (ViewHolder) holder;
        String name= dataList.get(position);
        if (!TextUtils.isEmpty(name)){
            viewHolder.txt_menu.setText(name);
        }
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null){
                    itemClickListener.onItemClick(v,position);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_menu;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_menu = (TextView) rootView.findViewById(R.id.txt_menu);
        }

    }
}

package com.sineverything.news.ui.my.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sineverything.news.R;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.my.activity.EditAddressActivity;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/8.
 * email : huangjinping@hdfex.com
 */

public class AddressAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String> dataList;

    public AddressAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    public MyItemClickListener itemClickListener;

    public void setItemClickListener(MyItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_address, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditAddressActivity.startAction(v.getContext());
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_address;
        public TextView txt_address_edit;
        public TextView txt_address_delete;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_address = (TextView) rootView.findViewById(R.id.txt_address);
            this.txt_address_edit = (TextView) rootView.findViewById(R.id.txt_address_edit);
            this.txt_address_delete = (TextView) rootView.findViewById(R.id.txt_address_delete);
        }

    }
}

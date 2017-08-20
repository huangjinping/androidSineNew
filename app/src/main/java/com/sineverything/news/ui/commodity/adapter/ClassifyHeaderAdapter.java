package com.sineverything.news.ui.commodity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.sineverything.news.R;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/20.
 * email : huangjinping@hdfex.com
 */

public class ClassifyHeaderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<String>  dataList;

    public ClassifyHeaderAdapter(List<String> dataList) {
        this.dataList = dataList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_classify_header, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ViewHolder viewHolder= (ViewHolder) holder;

    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_header;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_header = (ImageView) rootView.findViewById(R.id.img_header);
        }

    }
}

package com.sineverything.news.ui.commodity.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.andview.refreshview.callback.IFooterCallBack;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.MenuItem;
import com.sineverything.news.comm.MyItemClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/8/19.
 * email : huangjinping@hdfex.com
 */

public class ClassifyContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MenuItem> dataList;
    private static final int HEADER = 1;
    private static final int ITEM = 2;
    final List<String> mClassify = new ArrayList<>();


    private MyItemClickListener itemClickListener;

    public void setItemClickListener(MyItemClickListener itemClickListener) {
        this.itemClickListener = itemClickListener;
    }

    public ClassifyContentAdapter(List<MenuItem> dataList) {
        this.dataList = dataList;

    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        if (viewType == HEADER) {
//            View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_classify_header, parent, false);
//            return new HeaderViewHolder(item);
//        } else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_classify, parent, false);
            return new ViewHolder(view);
//        }
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
//        if (position == 0) {
//            HeaderViewHolder headerViewHolder = (HeaderViewHolder) holder;
//            Context context = headerViewHolder.rec_clsssify_header.getContext();
//            headerViewHolder.rec_clsssify_header.setLayoutManager(new LinearLayoutManager(context));
//            ClassifyHeaderAdapter mAdapter = new ClassifyHeaderAdapter(mClassify);
//            headerViewHolder.rec_clsssify_header.setAdapter(mAdapter);
//        }
        ViewHolder viewHolder= (ViewHolder) holder;

        MenuItem item = dataList.get(position);
        if (!TextUtils.isEmpty(item.getClassName())){
            viewHolder.txt_commodity_name.setText(item.getClassName());
        }


        viewHolder.rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (itemClickListener!=null){
                    itemClickListener.onItemClick(v,position);
                }
            }
        });
        ImageLoaderUtils.display(viewHolder.txt_commodity_name.getContext(),viewHolder.img_item,item.getAppIcon());

    }

    @Override
    public int getItemViewType(int position) {
//        if (position == 0) {
//            return HEADER;
//        } else {
            return ITEM;
//        }
    }

    @Override
    public int getItemCount() {
        return dataList.size();

    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public ImageView img_item;
        public TextView txt_commodity_name;

        public ViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.img_item = (ImageView) rootView.findViewById(R.id.img_item);
            this.txt_commodity_name = (TextView) rootView.findViewById(R.id.txt_commodity_name);
        }
    }

    public static class HeaderViewHolder extends RecyclerView.ViewHolder {
        public View rootView;
        public TextView txt_new_title;
        public RecyclerView rec_clsssify_header;

        public HeaderViewHolder(View rootView) {
            super(rootView);
            this.rootView = rootView;
            this.txt_new_title = (TextView) rootView.findViewById(R.id.txt_new_title);
            this.rec_clsssify_header = (RecyclerView) rootView.findViewById(R.id.rec_clsssify_header);
        }

    }
}

package com.sineverything.news.ui.service.adapter;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Classify;
import com.sineverything.news.bean.commodity.MenuItem;
import com.sineverything.news.bean.service.ChildMenu;
import com.sineverything.news.bean.service.ServiceMenu;
import com.sineverything.news.comm.MyItemClickListener;
import com.sineverything.news.ui.commodity.adapter.HomeTopMenuAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * author Created by harrishuang on 2017/8/7.
 * email : huangjinping@hdfex.com
 */

public class ServiceTopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {



    private Context mContext;
    private List<Classify> mList;

    public void setmListener(MyItemClickListener mListener) {
        this.mListener = mListener;
    }

    private MyItemClickListener mListener;

    public List<Classify> getmList() {
        return mList;
    }

    public void setmList(List<Classify> mList) {
        this.mList = mList;
    }


    public ServiceTopAdapter(Context context, List<Classify> mCList) {

        this.mList = mCList;
        this.mContext = context;
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_home_top_classify_list, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder viewHolder, final int position) {
        ViewHolder holder= (ViewHolder) viewHolder;

        Drawable micon = mContext.getResources().getDrawable(mList.get(position).getIcon());
        holder.ivPic.setImageDrawable(micon);
        holder.tvTitle.setText(mList.get(position).getTitle());
        //设置监听
        if (mListener != null) {
            holder.llRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mListener!=null){
                        mListener.onItemClick(v, position);
                    }

                }
            });
        }
    }


    // Return the size of your dataset (invoked by the layout manager)
    @Override
    public int getItemCount() {
        return mList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivPic;
        TextView tvTitle;
        LinearLayout llRoot;

        ViewHolder(View view) {
            super(view);
            ivPic = (ImageView) view.findViewById(R.id.iv_pic);
            tvTitle = (TextView) view.findViewById(R.id.tv_title);
            llRoot = (LinearLayout) view.findViewById(R.id.ll_root);
        }

    }
}

package com.sineverything.news.ui.commodity.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jaydenxiao.common.commonutils.ImageLoaderUtils;
import com.sineverything.news.R;
import com.sineverything.news.bean.commodity.Classify;
import com.sineverything.news.bean.commodity.MenuItem;
import com.sineverything.news.comm.MyItemClickListener;

import java.util.List;

/**
 * author Created by harrishuang on 2017/8/5.
 * email : huangjinping@hdfex.com
 */

public class HomeTopMenuAdapter  extends RecyclerView.Adapter<HomeTopMenuAdapter.ViewHolder>{


    private Context mContext;
    private List<MenuItem> mList;

    public void setmListener(MyItemClickListener mListener) {
        this.mListener = mListener;
    }

    private MyItemClickListener mListener;

    public List<MenuItem> getmList() {
        return mList;
    }

    public void setmList(List<MenuItem> mList) {
        this.mList = mList;
    }


    public HomeTopMenuAdapter(Context context, List<MenuItem> mCList) {

        this.mList = mCList;
        this.mContext = context;
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        // Create a new view.
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_home_top_classify_list, viewGroup, false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, final int position) {
        MenuItem item = mList.get(position);


        if (item.getDefaultId()!=0){
            Drawable micon = mContext.getResources().getDrawable(mList.get(position).getDefaultId());
            viewHolder.ivPic.setImageDrawable(micon);
        }else {
            Glide.with(mContext).load(item.getAppIcon()).placeholder(R.mipmap.ic_icon_detault).error(R.mipmap.ic_icon_detault).into(viewHolder.ivPic);
        }
        viewHolder.tvTitle.setText(mList.get(position).getClassName());



        //设置监听
        if (mListener != null) {
            viewHolder.llRoot.setOnClickListener(new View.OnClickListener() {
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

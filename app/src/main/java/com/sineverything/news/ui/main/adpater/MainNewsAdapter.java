package com.sineverything.news.ui.main.adpater;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.cameroon.banner.Banner;
import com.cameroon.banner.listener.OnBannerListener;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.NewsItem;
import com.sineverything.news.comm.GlideImageLoader;
import com.sineverything.news.ui.main.activity.NewsDetailsActivity;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * author Created by harrishuang on 2017/5/15.
 * email : huangjinping@hdfex.com
 */

public class MainNewsAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {


    public final int HEAD_TYPE = 1234;

    public final int ITEM_TYPE = 888;
    List<NewsItem> dataList;
    List<com.sineverything.news.bean.main.Banner> banners;


    public MainNewsAdapter(List<NewsItem> dataList, List<com.sineverything.news.bean.main.Banner> banners) {
        this.dataList = dataList;
        this.banners = banners;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case HEAD_TYPE:
                view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newheader,parent,false);
                return new ViewHeaderHolder(view);
            default:
                view=  LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_newitem,parent,false);
                return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
           final ViewHeaderHolder headHolder = (ViewHeaderHolder) holder;
            GlideImageLoader glideImageLoader = new GlideImageLoader();
            headHolder.banner.setImageLoader(glideImageLoader);
            headHolder.banner.setImages(banners);
            headHolder.banner.isAutoPlay(true);
            //设置轮播时间
            headHolder.banner.setDelayTime(1500);
            headHolder.banner.start();
            headHolder.banner.setOnBannerListener(new OnBannerListener() {
                @Override
                public void OnBannerClick(int i) {
                    NewsDetailsActivity.startAction(headHolder.banner.getContext(), banners.get(i).getId() + "");
                }
            });
        } else {
            ViewHolder viewHolder = (ViewHolder) holder;
            final NewsItem newsItem = dataList.get(position - 1);
            Glide.with(viewHolder.imgNewIcon.getContext()).load(newsItem.getCover()).placeholder(R.mipmap.ic_fuzhuang_default).error(R.mipmap.ic_fuzhuang_default).into(viewHolder.imgNewIcon);

            if (!TextUtils.isEmpty(newsItem.getTitle())){
                viewHolder.txtNewTitle.setText(newsItem.getTitle());
            }
            if (!TextUtils.isEmpty(newsItem.getCreateTime())){
                viewHolder.txtCreateDate.setText(newsItem.getCreateTime());
            }

            if (!TextUtils.isEmpty(newsItem.getSource())){
                viewHolder.txtSource.setText(newsItem.getSource());
            }

            viewHolder.layoutIcon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    NewsDetailsActivity.startAction(v.getContext(), newsItem.getId() + "");
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return dataList.size() + 1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }


    static class ViewHeaderHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.banner)
        Banner banner;

        ViewHeaderHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }


    static class ViewHolder  extends RecyclerView.ViewHolder{
        @Bind(R.id.img_new_icon)
        ImageView imgNewIcon;
        @Bind(R.id.txt_new_title)
        TextView txtNewTitle;
        @Bind(R.id.txt_createDate)
        TextView txtCreateDate;
        @Bind(R.id.txt_source)
        TextView txtSource;
        @Bind(R.id.layout_icon)
        LinearLayout layoutIcon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}

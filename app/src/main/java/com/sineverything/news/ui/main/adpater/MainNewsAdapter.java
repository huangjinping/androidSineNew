package com.sineverything.news.ui.main.adpater;

import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.andview.refreshview.recyclerview.BaseRecyclerAdapter;
import com.bumptech.glide.Glide;
import com.sineverything.news.R;
import com.sineverything.news.bean.main.NewsItem;
import com.sineverything.news.comm.GlideImageLoader;
import com.sineverything.news.ui.main.activity.NewsDetailsActivity;
import com.youth.banner.Banner;

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
    List<NewsItem> banners;


    public MainNewsAdapter(List<NewsItem> dataList, List<NewsItem> banners) {
        this.dataList = dataList;
        this.banners = banners;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = null;
        switch (viewType) {
            case HEAD_TYPE:
                view = View.inflate(parent.getContext(), R.layout.layout_newheader, null);
                return new ViewHeaderHolder(view);
            default:
                view = View.inflate(parent.getContext(), R.layout.layout_newitem, null);
                return new ViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (position == 0) {
            ViewHeaderHolder headHolder = (ViewHeaderHolder) holder;
            GlideImageLoader glideImageLoader = new GlideImageLoader();
            headHolder.banner.setImageLoader(glideImageLoader);
            headHolder.banner.setImages(banners);
            headHolder.banner.isAutoPlay(true);
            //设置轮播时间
            headHolder.banner.setDelayTime(1500);
            headHolder.banner.start();
        } else {
            ViewHolder viewHolder = (ViewHolder) holder;
            final NewsItem newsItem = dataList.get(position - 1);
            Glide.with(viewHolder.imgNewIcon.getContext()).load(newsItem.getPic()).into(viewHolder.imgNewIcon);
            if (newsItem.getTitle() != null && !TextUtils.isEmpty(newsItem.getTitle().getRendered())) {
                viewHolder.txtNewTitle.setText(Html.fromHtml(newsItem.getTitle().getRendered()));
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
        return dataList.size()+1;
    }


    @Override
    public int getItemViewType(int position) {
        if (position == 0) {
            return HEAD_TYPE;
        } else {
            return ITEM_TYPE;
        }
    }




//    @Override
//    public RecyclerView.ViewHolder getViewHolder(View view) {
//        return null;
//    }
//
//    @Override
//    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType, boolean isItem) {
//
//        View view = null;
//        switch (viewType) {
//            case HEAD_TYPE:
//                view = View.inflate(parent.getContext(), R.layout.layout_newheader, null);
//                return new ViewHeaderHolder(view);
//            default:
//                view = View.inflate(parent.getContext(), R.layout.layout_newitem, null);
//                return new ViewHolder(view);
//        }
//    }
//
//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position, boolean isItem) {
//        if (position == 0) {
//            ViewHeaderHolder headHolder = (ViewHeaderHolder) holder;
//            GlideImageLoader glideImageLoader = new GlideImageLoader();
//            headHolder.banner.setImageLoader(glideImageLoader);
//            headHolder.banner.setImages(banners);
//            headHolder.banner.isAutoPlay(true);
//            //设置轮播时间
//            headHolder.banner.setDelayTime(1500);
//            headHolder.banner.start();
//        } else {
//            ViewHolder viewHolder = (ViewHolder) holder;
//            final NewsItem newsItem = dataList.get(position - 1);
//            Glide.with(viewHolder.imgNewIcon.getContext()).load(newsItem.getPic()).into(viewHolder.imgNewIcon);
//            if (newsItem.getTitle() != null && !TextUtils.isEmpty(newsItem.getTitle().getRendered())) {
//                viewHolder.txtNewTitle.setText(Html.fromHtml(newsItem.getTitle().getRendered()));
//            }
//            viewHolder.layoutIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    NewsDetailsActivity.startAction(v.getContext(), newsItem.getId() + "");
//                }
//            });
//        }
//    }
//
//
//    @Override
//    public int getAdapterItemViewType(int position) {
//
//        if (position == 0) {
//            return HEAD_TYPE;
//        } else {
//            return ITEM_TYPE;
//        }
//
//    }
//
//
//    @Override
//    public int getAdapterItemCount() {
//        return dataList.size();
//    }

//
//    @Override
//    public int getItemViewType(int position) {
//        if (position == 0) {
//            return HEAD_TYPE;
//        } else {
//            return ITEM_TYPE;
//        }
//
//    }

//    @Override
//    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
//        if (position == 0) {
//            ViewHeaderHolder headHolder = (ViewHeaderHolder) holder;
//            GlideImageLoader glideImageLoader = new GlideImageLoader();
//            headHolder.banner.setImageLoader(glideImageLoader);
//            headHolder.banner.setImages(banners);
//            Log.d("zajiaxiaozi","======"+banners.size());
//            headHolder.banner.isAutoPlay(true);
//            //设置轮播时间
//            headHolder.banner.setDelayTime(1500);
//            headHolder.banner.start();
//        } else {
//            ViewHolder viewHolder = (ViewHolder) holder;
//            final NewsItem newsItem = dataList.get(position - 1);
//            Glide.with(viewHolder.imgNewIcon.getContext()).load(newsItem.getPic()).into(viewHolder.imgNewIcon);
//            if (newsItem.getTitle() != null && !TextUtils.isEmpty(newsItem.getTitle().getRendered())) {
//                viewHolder.txtNewTitle.setText(Html.fromHtml(newsItem.getTitle().getRendered()));
//            }
//            viewHolder.layoutIcon.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    NewsDetailsActivity.startAction(v.getContext(), newsItem.getId() + "");
//                }
//            });
//        }
//    }

//    @Override
//    public int getItemCount() {
//        return dataList.size();
//    }


    static class ViewHeaderHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.banner)
        Banner banner;

        ViewHeaderHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @Bind(R.id.img_new_icon)
        ImageView imgNewIcon;
        @Bind(R.id.txt_new_title)
        TextView txtNewTitle;
        @Bind(R.id.txt_new_content)
        TextView txtNewContent;
        @Bind(R.id.layout_icon)
        LinearLayout layoutIcon;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
